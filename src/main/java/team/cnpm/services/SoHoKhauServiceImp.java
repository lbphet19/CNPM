package team.cnpm.services;

//import java.security.acl.Owner;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import team.cnpm.DTOs.request.CongDanOfSHKRequestDTO;
import team.cnpm.DTOs.request.SoHoKhauRequestDTO;
import team.cnpm.DTOs.response.CongDanOfSHK_DTO;
import team.cnpm.DTOs.response.CongDanResponseDTO;
import team.cnpm.DTOs.response.SoHoKhauDetailDTO;
import team.cnpm.DTOs.response.SoHoKhauResponseDTO;
import team.cnpm.exceptions.OwnerNotAvailableException;
import team.cnpm.models.CongDan;
import team.cnpm.models.SoHoKhau;
import team.cnpm.models.SoHoKhauHistory;
import team.cnpm.repositories.CongDanRepository;
import team.cnpm.repositories.SoHoKhauRepository;
import team.cnpm.specifications.SHKSpecification;
import team.cnpm.utils.IdSHKGenerator;

@Service
public class SoHoKhauServiceImp implements SoHoKhauService {

	@Autowired
	private IdSHKGenerator idGenerator;
	@Autowired
	private SoHoKhauHistoryService shkHistoryService;

	@Autowired
	private CongDanRepository congDanRepo;

	@Autowired
	private SoHoKhauRepository hoKhauRepo;

	@Autowired
	private CongDanService congDanService;

	public List<SoHoKhau> get() {
		return this.hoKhauRepo.findAll();
	}

	public SoHoKhau save(SoHoKhau hoKhau) {
//		gen random string
		String shkId;
		do {
			shkId = this.idGenerator.getSaltString();
		} while (this.hoKhauRepo.existsById(shkId));
		hoKhau.setId(shkId);
		return this.hoKhauRepo.save(hoKhau);
	}

	public SoHoKhau update(SoHoKhau hoKhauUpdate) {
		SoHoKhau hoKhau = this.hoKhauRepo.findBySHKId(hoKhauUpdate.getId());
		hoKhau.setAddress(hoKhauUpdate.getAddress());
		return this.hoKhauRepo.save(hoKhau);
	}

	public SoHoKhau update(SoHoKhauRequestDTO hoKhauUpdateRequestDTO) {
		SoHoKhau hoKhau = this.hoKhauRepo.findBySHKId(hoKhauUpdateRequestDTO.getId());
		hoKhau.setAddress(hoKhauUpdateRequestDTO.getAddress());
		return this.hoKhauRepo.save(hoKhau);
	}

	public String delete(String id) {
		try {
			this.hoKhauRepo.deleteById(id);
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
			// TODO: handle exception
		}
	}

	public SoHoKhau updateMembers(SoHoKhau hoKhau, int idChuHo, List<CongDanOfSHKRequestDTO> members) throws OwnerNotAvailableException {
		CongDan chuHo = this.congDanRepo.findById(idChuHo).get();
		if(chuHo.getHoKhauSoHuu() != null) throw new OwnerNotAvailableException();
		List<Integer> membersId = members.stream().map(congDan -> congDan.getId()).collect(Collectors.toList());
		CongDan oldChuHo = hoKhau.getOwner();
		if (hoKhau.getOwner() == null || idChuHo != hoKhau.getOwner().getId()) {

			if (hoKhau.getOwner() != null) {
				if (!membersId.contains(hoKhau.getOwner().getId())) {
					this.shkHistoryService.save(new SoHoKhauHistory("Chuyển hộ", Date.valueOf(LocalDate.now()),
							"Rời đi", hoKhau.getOwner(), hoKhau, null));
				}
			}
			if (!hoKhau.getMembers().contains(chuHo)) {
				if (chuHo.getHoKhauSoHuu() == null) {
					this.shkHistoryService.save(new SoHoKhauHistory("Chuyển hộ", Date.valueOf(LocalDate.now()),
							"Chuyển đến", chuHo, null, hoKhau));
				}
				;

				if (chuHo.getHoKhauSoHuu() != null) {
					this.shkHistoryService.save(new SoHoKhauHistory("Chuyển hộ", Date.valueOf(LocalDate.now()),
							"Chuyển đến", chuHo, chuHo.getHoKhauSoHuu(), hoKhau));
					SoHoKhau oldHoKhau = chuHo.getHoKhauSoHuu();
					oldHoKhau.setOwner(null);
					oldHoKhau.removeMember(chuHo);
					this.hoKhauRepo.save(oldHoKhau);
				}
			}

			chuHo.setHoKhau(hoKhau);
			hoKhau.setOwner(chuHo);
			chuHo.setRelationship("Chủ hộ");

		}
//		them chu ho vao list thanh vien moi
		if (!membersId.contains(idChuHo)) {
			membersId.add(idChuHo);
		}

		List<Integer> oldMembersId = hoKhau.getMembers().stream().map(congDan -> congDan.getId())
				.collect(Collectors.toList());

		// loc oldmemeber voi memberid
		List<Integer> membersToRemove = this.findUniqueElements(oldMembersId, membersId);
		List<Integer> membersToUpdate = this.findUniqueElements(membersId, oldMembersId);
		// update ho khau thanh vien
		this.updateNewMembers(membersToUpdate, chuHo, hoKhau);
		this.removeOldMembers(membersToRemove, oldChuHo, hoKhau);
//		 update relations
		for(CongDanOfSHKRequestDTO cd:members) {
			this.congDanService.updateRelationship(cd);
		}
//		members.stream().map(congDan -> this.congDanService.updateRelationship(congDan));
		return this.hoKhauRepo.save(hoKhau);
	}

	public SoHoKhau dtoToEntity(SoHoKhauRequestDTO dto) {
		return new SoHoKhau(dto.getId(), dto.getAddress());
	}

	private void updateNewMembers(List<Integer> membersToUpdate, CongDan chuHo, SoHoKhau hoKhau) {
		if (membersToUpdate != null) {
			membersToUpdate.stream().map(id -> this.congDanRepo.findById(id).get()).forEach(congDan -> {
				if (!congDan.equals(chuHo)) {
					this.shkHistoryService.save(new SoHoKhauHistory("Chuyển hộ", Date.valueOf(LocalDate.now()),
							"Chuyển đến", congDan, congDan.getHoKhau(), hoKhau));
				}
				// congdan remove ho khau cu
				// them vao lichsu(congdan,ho cu,roidi);(congdan, ho moi, den);
//				congDan.setHoKhau(null);
				hoKhau.addMember(congDan);
			});
		}
	}

	private void removeOldMembers(List<Integer> membersToRemove, CongDan oldChuHo, SoHoKhau hoKhau) {
		if (membersToRemove != null) {
			membersToRemove.stream().map(id -> this.congDanRepo.findById(id).get()).forEach(congDan -> {
				if (!congDan.equals(oldChuHo)) {
					this.shkHistoryService.save(new SoHoKhauHistory("Chuyển hộ", Date.valueOf(LocalDate.now()),
							"Rời đi", congDan, hoKhau, null));
				}
				hoKhau.removeMember(congDan);
				congDan.setRelationship(null);
				this.congDanService.updateRelationship(congDan);
			});
		}
	}

//loc cac element trong list1 ko co trong list2
	private List<Integer> findUniqueElements(List<Integer> list1, List<Integer> list2) {
		if (list1 == null)
			return null;
		if (list2 == null)
			return list1;
		List<Integer> res = new ArrayList<Integer>();
		Set<Integer> list2AsSet = new HashSet<Integer>(list2);
		for (Integer i : list1) {
			if (!list2AsSet.contains(i)) {
				res.add(i);
			}
		}
		return res;
	}

	public SoHoKhauResponseDTO entityToDTO(SoHoKhau shk) {
		return new SoHoKhauResponseDTO(shk.getId(), shk.getOwner().getFirstName() + " " + shk.getOwner().getLastName(),
				shk.getAddress(), shk.getOwner().getPhoneNumber(), shk.getMembers().size());
	}

	public SoHoKhauDetailDTO entityToDetailDTO(SoHoKhau shk) {
		List<CongDan> members = shk.getMembers();

		List<CongDanOfSHK_DTO> membersDTO = new ArrayList<CongDanOfSHK_DTO>();
		for (CongDan m : members)
			membersDTO.add(this.congDanService.entityToCDofSHK_DTO(m));

		return new SoHoKhauDetailDTO(shk.getId(), shk.getOwner().getFirstName() + " " + shk.getOwner().getLastName(),
				shk.getAddress(), shk.getOwner().getPhoneNumber(), shk.getMembers().size(), membersDTO);
	}

	public SoHoKhau getByID(String i) {
		return this.hoKhauRepo.findById(i).get();
	}

	public List<SoHoKhau> findSHKByName(String fname, String lname, String cccd) {
		List<SoHoKhau> shk = new ArrayList<SoHoKhau>();

		List<String> args = new ArrayList<String>();
		args.add(fname);
		args.add(lname);
		args.add(cccd);
		// Kiem tra neu all args = null => return shk (empty list)
		int checkAllNull = 0;
		for (int i = 0; i < args.size(); i++)
			if (args.get(i) != null)
				if (args.get(i).isBlank()) {
					args.set(i, null);
					checkAllNull++;
				} else
					args.set(i, args.get(i).trim());
			else
				checkAllNull++;

		if (checkAllNull == args.size())
			return shk;

		// Neu co it nhat 1 arg != null => tiep tuc truy van
		Specification<SoHoKhau> spec = Specification.where(SHKSpecification.ownerFnameLike(args.get(0)))
				.and(SHKSpecification.ownerLnameLike(args.get(1)))
				.and(SHKSpecification.ownerCCCDLike(args.get(2)));
		shk = this.hoKhauRepo.findAll(spec);
		return shk;
	}

	@Override
	public String delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}

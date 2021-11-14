package team.cnpm.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import team.cnpm.DTOs.request.SoHoKhauRequestDTO;
import team.cnpm.models.CongDan;
import team.cnpm.models.SoHoKhau;
import team.cnpm.repositories.CongDanRepository;
import team.cnpm.repositories.SoHoKhauRepository;

@Service
public class SoHoKhauServiceImp implements SoHoKhauService {
	@Autowired
	private CongDanRepository congDanRepo;

	@Autowired
	private SoHoKhauRepository hoKhauRepo;

	public List<SoHoKhau> get() {
		return this.hoKhauRepo.findAll();
	}

	public SoHoKhau save(SoHoKhau hoKhau) {
		return this.hoKhauRepo.save(hoKhau);
	}

	public SoHoKhau update(SoHoKhau hoKhauUpdate) {
		SoHoKhau hoKhau = this.hoKhauRepo.findById(hoKhauUpdate.getId()).get();
		hoKhau.setAddress(hoKhauUpdate.getAddress());
		return this.hoKhauRepo.save(hoKhau);
	}

	public SoHoKhau update(SoHoKhauRequestDTO hoKhauUpdateRequestDTO) {
		SoHoKhau hoKhau = this.hoKhauRepo.findById(hoKhauUpdateRequestDTO.getId()).get();
		hoKhau.setAddress(hoKhauUpdateRequestDTO.getAddress());
		return this.hoKhauRepo.save(hoKhau);
	}

	public String delete(int id) {
		try {
			this.hoKhauRepo.deleteById(id);
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
			// TODO: handle exception
		}
	}

	public SoHoKhau updateMembers(SoHoKhau hoKhau, Integer idChuHo, List<Integer> membersId) {
		if (idChuHo != 0) {
			CongDan chuHo = this.congDanRepo.findById(idChuHo).get();
			// set chu Ho 1-1 voi ho khau
			chuHo.setHoKhauSoHuu(hoKhau);
			hoKhau.setOwner(chuHo);
		} else {
			hoKhau.getOwner().setHoKhauSoHuu(null);
			hoKhau.setOwner(null);
		}
		List<Integer> oldMembersId = hoKhau.getMembers().stream().map(congDan -> congDan.getId())
				.collect(Collectors.toList());

		// loc oldmemeber voi memberid
		List<Integer> membersToRemove = this.findUniqueElements(oldMembersId, membersId);
		List<Integer> membersToUpdate = this.findUniqueElements(membersId, oldMembersId);
		// update ho khau thanh vien
		membersToUpdate.stream().map(id -> this.congDanRepo.findById(id).get()).forEach(congDan -> {
			hoKhau.addMember(congDan);
		});
		membersToRemove.stream().map(id -> this.congDanRepo.findById(id).get())
				.forEach(congDan -> hoKhau.removeMember(congDan));
		return this.hoKhauRepo.save(hoKhau);
	}

	public SoHoKhau dtoToEntity(SoHoKhauRequestDTO dto) {
		return new SoHoKhau(dto.getId(), dto.getAddress());
	}

	// loc cac element trong list1 ko co trong list2
	private List<Integer> findUniqueElements(List<Integer> list1, List<Integer> list2) {
		List<Integer> res = new ArrayList<Integer>();
		Set<Integer> list2AsSet = new HashSet<Integer>(list2);
		for (Integer i : list1) {
			if (!list2AsSet.contains(i)) {
				res.add(i);
			}
		}
		return res;
	}
}

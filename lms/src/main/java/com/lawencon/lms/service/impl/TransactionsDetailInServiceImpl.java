package com.lawencon.lms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.lawencon.lms.dao.PermissionsDao;
import com.lawencon.lms.dao.PermissionsRolesDao;
import com.lawencon.lms.dao.RolesDao;
import com.lawencon.lms.dao.TransactionsDetailInDao;
import com.lawencon.lms.dao.UsersDao;
import com.lawencon.lms.dto.transactionsin.GetAllTransactionsDetailInResDto;
import com.lawencon.lms.dto.transactionsin.GetByTransactionsDetailInCodeResDto;
import com.lawencon.lms.dto.transactionsin.GetTransactionsDetailsInDataDto;
import com.lawencon.lms.dto.transactionsout.GetTransactionsDetailsOutDataDto;
import com.lawencon.lms.model.Permissions;
import com.lawencon.lms.model.PermissionsRoles;
import com.lawencon.lms.model.Roles;
import com.lawencon.lms.model.TransactionsDetailIn;
import com.lawencon.lms.model.TransactionsDetailOut;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.TransactionsDetailInService;

@Service
public class TransactionsDetailInServiceImpl extends BaseServiceLmsImpl implements TransactionsDetailInService {

	@Autowired
	private TransactionsDetailInDao transactionsDetailInDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private PermissionsDao permissionsDao;

	@Autowired
	private RolesDao rolesDao;

	@Autowired
	private PermissionsRolesDao permissionsRolesDao;

	@Override
	public GetAllTransactionsDetailInResDto findByTransactionInCode(String code) throws Exception {
		GetAllTransactionsDetailInResDto resDto = new GetAllTransactionsDetailInResDto();
		List<GetTransactionsDetailsInDataDto> listInDataDto = new ArrayList<GetTransactionsDetailsInDataDto>();
		List<TransactionsDetailIn> listIn = transactionsDetailInDao.findByTransactionInCode(code);

		listIn.forEach(detail -> {
			GetTransactionsDetailsInDataDto data = new GetTransactionsDetailsInDataDto();
			data.setTransactionsInCode(detail.getTransactionsIn().getTransactionsInCode());
			if(detail.getLocations()!=null) {
				data.setLocationsId(detail.getLocations().getId());
			}
			if(detail.getEmployees()!=null) {
				data.setEmployeesId(detail.getEmployees().getId());
				data.setEmployeesCode(detail.getEmployees().getEmployeesCode());
			}
			if(detail.getAssets()!=null) {
				data.setEmployeesId(detail.getEmployees().getId());
				data.setEmployeesCode(detail.getEmployees().getEmployeesCode());
			}
			data.setAssetsId(detail.getAssets().getId());
			data.setAssetsName(detail.getAssets().getAssetsName());
			data.setReturnDate(detail.getReturnDate().toString());
			data.setVersion(detail.getVersion());
			data.setCreatedBy(detail.getCreatedBy());
			data.setCreatedAt(detail.getCreatedAt());
			data.setUpdatedBy(detail.getUpdatedBy());
			data.setUpdatedAt(detail.getUpdatedAt());

			listInDataDto.add(data);
		});

		resDto.setGetTransactionsDetailsInDataDto(listInDataDto);
		resDto.setMessage("SUCCESS");
		return resDto;
	}

	public Boolean validationUsers(String permissionsCode) throws Exception {
		try {
			Users users = usersDao.findById(getIdAuth());
			Roles roles = rolesDao.findById(users.getRoles().getId());
			Permissions permissions = permissionsDao.findByCode(permissionsCode);
			List<PermissionsRoles> listPermissionsRoles = permissionsRolesDao.findAll();
			for (int i = 0; i < listPermissionsRoles.size(); i++) {
				if (listPermissionsRoles.get(i).getPermissions().getId().equals(permissions.getId())) {
					if (listPermissionsRoles.get(i).getRoles().getId().equals(roles.getId())) {
						return true;
					}
				}
			}
			return false;
		} catch (NotFoundException e) {
			throw new Exception(e);
		}
	}
}

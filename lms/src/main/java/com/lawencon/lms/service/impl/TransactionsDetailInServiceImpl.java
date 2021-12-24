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
import com.lawencon.lms.dto.transactionsin.GetByTransactionsDetailInCodeResDto;
import com.lawencon.lms.dto.transactionsin.GetTransactionsDetailsInDataDto;
import com.lawencon.lms.model.Permissions;
import com.lawencon.lms.model.PermissionsRoles;
import com.lawencon.lms.model.Roles;
import com.lawencon.lms.model.TransactionsDetailIn;
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
	public GetByTransactionsDetailInCodeResDto findByTransactionInCode(String code) throws Exception {

		String permissionsCode = "PERMSN35";
		Boolean validation = validationUsers(permissionsCode);
		if (validation) {

			GetByTransactionsDetailInCodeResDto findByCodeTransactionInDto = new GetByTransactionsDetailInCodeResDto();
			List<GetTransactionsDetailsInDataDto> listTdinDataDto = new ArrayList<>();
			List<TransactionsDetailIn> listTdin = transactionsDetailInDao.findByTransactionInCode(code);

			listTdin.forEach(listDetail -> {
				GetTransactionsDetailsInDataDto tdinDataDto = new GetTransactionsDetailsInDataDto();
				tdinDataDto.setId(listDetail.getId());
				tdinDataDto.setTransactionsInCode(listDetail.getTransactionsIn().getTransactionsInCode());
				tdinDataDto.setLocationsCode(listDetail.getLocations().getLocationsCode());
				tdinDataDto.setLocationsDeploy(listDetail.getLocations().getLocationsDeploy());
				tdinDataDto.setEmployeesId(listDetail.getEmployees().getId());
				tdinDataDto.setEmployeesCode(listDetail.getEmployees().getEmployeesCode());
				tdinDataDto.setEmployeesFullname(listDetail.getEmployees().getEmployeesFullname());
				tdinDataDto.setAssetsId(listDetail.getAssets().getId());
				tdinDataDto.setAssetsName(listDetail.getAssets().getAssetsName());
				tdinDataDto.setStatusesTransactionsId(listDetail.getStatusesTransactions().getId());
				tdinDataDto.setTransactionsInCode(listDetail.getStatusesTransactions().getStatusesTransactionsCode());
				tdinDataDto.setStatusesTransactionsName(listDetail.getStatusesTransactions().getStatusesTransactionsName());
				tdinDataDto.setVersion(listDetail.getVersion());
				tdinDataDto.setCreatedAt(listDetail.getCreatedAt());
				tdinDataDto.setCreatedBy(listDetail.getCreatedBy());
				tdinDataDto.setUpdatedAt(listDetail.getUpdatedAt());
				tdinDataDto.setUpdatedBy(listDetail.getUpdatedBy());
				tdinDataDto.setIsActive(listDetail.getIsActive());

				listTdinDataDto.add(tdinDataDto);
			});

			findByCodeTransactionInDto.setGetTransactionsDetailsInDataDto(listTdinDataDto);
			findByCodeTransactionInDto.setMessage("SUCCESS");

			return findByCodeTransactionInDto;
		}
		throw new Exception("Access Denied");

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

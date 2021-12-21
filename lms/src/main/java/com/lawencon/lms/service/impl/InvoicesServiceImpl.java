package com.lawencon.lms.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.lawencon.lms.constant.EnumCode;
import com.lawencon.lms.dao.InvoicesDao;
import com.lawencon.lms.dao.PermissionsDao;
import com.lawencon.lms.dao.PermissionsRolesDao;
import com.lawencon.lms.dao.RolesDao;
import com.lawencon.lms.dao.UsersDao;
import com.lawencon.lms.dto.invoices.SaveInvoicesResDto;
import com.lawencon.lms.dto.invoices.UpdateInvoicesResDto;
import com.lawencon.lms.model.Invoices;
import com.lawencon.lms.model.Permissions;
import com.lawencon.lms.model.PermissionsRoles;
import com.lawencon.lms.model.Roles;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.InvoicesService;

@Service
public class InvoicesServiceImpl extends BaseServiceLmsImpl implements InvoicesService {

	@Autowired
	private InvoicesDao invoicesDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private RolesDao rolesDao;

	@Autowired
	private PermissionsDao permissionsDao;

	@Autowired
	private PermissionsRolesDao permissionsRolesDao;

	@Override
	public List<Invoices> findAll() throws Exception {

		String permissionsCode = "PERMSN21";
		Boolean validation = validationUsers(permissionsCode);
		if (validation)
			return invoicesDao.findAll();
		throw new Exception("Access Denied");
	}

	@Override
	public Invoices findById(String id) throws Exception {
		String permissionsCode = "PERMSN21";
		Boolean validation = validationUsers(permissionsCode);
		if (validation)
			return invoicesDao.findById(id);
		throw new Exception("Access Denied");
	}

	@Override
	public Invoices findByCode(String code) throws Exception {

		String permissionsCode = "PERMSN21";
		Boolean validation = validationUsers(permissionsCode);
		if (validation)
			return invoicesDao.findByCode(code);
		throw new Exception("Access Denied");
	}

	@Override
	public SaveInvoicesResDto save(Invoices invoices) throws Exception {
		SaveInvoicesResDto saveRes = new SaveInvoicesResDto();

		try {

			String permissionsCode = "PERMSN22";
			Boolean validation = validationUsers(permissionsCode);
			if (!validation) {
				throw new Exception("Access Denied");
			}

			else {
				if (invoices.getStoreName() == null || invoices.getStoreName().length() > 50) {
					throw new Exception("storeName required and not longer than 50 character length");
				}

				else if (invoices.getPrice() == null) {
					throw new Exception("price required");
				}

				else {
					begin();
					invoices.setInvoicesDate(LocalDateTime.now());
					invoices.setInvoicesCode(generateCode());
					invoices.setCreatedBy(getIdAuth());
					invoices = invoicesDao.saveOrUpdate(invoices);
					commit();

					saveRes.setId(invoices.getId());
					saveRes.setMessage("Inserted");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return saveRes;
	}

	@Override
	public UpdateInvoicesResDto update(Invoices invoices) throws Exception {
		UpdateInvoicesResDto updateRes = new UpdateInvoicesResDto();

		try {
			Invoices invoicesDb = findByCode(invoices.getInvoicesCode());
			invoicesDb.setUpdatedBy(getIdAuth());
			invoicesDb.setStoreName(invoices.getStoreName());
			invoicesDb.setPrice(invoices.getPrice());

			String permissionsCode = "PERMSN23";
			Boolean validation = validationUsers(permissionsCode);
			if (!validation) {
				throw new Exception("Access Denied");
			}

			else {
				begin();
				invoices = invoicesDao.saveOrUpdate(invoicesDb);
				commit();
				updateRes.setVersion(invoices.getVersion());
				updateRes.setMessage("Inserted");
			}

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return updateRes;
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		String permissionsCode = "PERMSN24";
		Boolean validation = validationUsers(permissionsCode);
		if (validation) {
			try {
				begin();
				boolean isDeleted = invoicesDao.removeById(id);
				commit();
				return isDeleted;
			} catch (Exception e) {
				e.printStackTrace();
				rollback();
				throw new Exception(e);
			}
		}

		throw new Exception("Access Denied");
	}

	public String generateCode() throws Exception {
		String generatedCode = EnumCode.INVOICES.getCode() + (invoicesDao.countData() + 1);
		return generatedCode;
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

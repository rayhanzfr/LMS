package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.lawencon.lms.constant.EnumCode;
import com.lawencon.lms.dao.ItemsBrandsDao;
import com.lawencon.lms.dao.PermissionsDao;
import com.lawencon.lms.dao.PermissionsRolesDao;
import com.lawencon.lms.dao.RolesDao;
import com.lawencon.lms.dao.UsersDao;
import com.lawencon.lms.dto.itemsbrands.SaveItemsBrandsResDto;
import com.lawencon.lms.dto.itemsbrands.UpdateItemsBrandsResDto;
import com.lawencon.lms.model.ItemsBrands;
import com.lawencon.lms.model.Permissions;
import com.lawencon.lms.model.PermissionsRoles;
import com.lawencon.lms.model.Roles;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.ItemsBrandsService;

@Service
public class ItemsBrandsServiceImpl extends BaseServiceLmsImpl implements ItemsBrandsService {

	@Autowired
	private ItemsBrandsDao itemsBrandsDao;

	@Autowired
	private PermissionsDao permissionsDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private RolesDao rolesDao;

	@Autowired
	private PermissionsRolesDao permissionsRolesDao;

	@Override
	public SaveItemsBrandsResDto save(ItemsBrands itemsBrands) throws Exception {
		String permissionsCode = "PERMSN30";
		Boolean validation = validationUsers(permissionsCode);
		if (validation) {
			SaveItemsBrandsResDto saveItemsBrandsResDto = new SaveItemsBrandsResDto();
			try {
				begin();
				itemsBrands.setItemsBrandsCode(generateCode());
				itemsBrands.setCreatedBy(getIdAuth());
				itemsBrands = itemsBrandsDao.saveOrUpdate(itemsBrands);
				commit();
				saveItemsBrandsResDto.setId(itemsBrands.getId());
				saveItemsBrandsResDto.setMsg("OK");
			} catch (Exception e) {
				e.printStackTrace();
				rollback();
			}
			return saveItemsBrandsResDto;
		}
		throw new Exception("Access Denied");
	}

	@Override
	public UpdateItemsBrandsResDto update(ItemsBrands itemsBrands) throws Exception {
		String permissionsCode = "PERMSN31";
		Boolean validation = validationUsers(permissionsCode);
		if (validation) {
			UpdateItemsBrandsResDto updateItemsBrandsResDto = new UpdateItemsBrandsResDto();
			try {
				ItemsBrands itemsBrandsDb = findByCode(itemsBrands.getItemsBrandsCode());
				itemsBrands.setCreatedAt(itemsBrandsDb.getCreatedAt());
				itemsBrands.setCreatedBy(itemsBrandsDb.getCreatedBy());
				itemsBrands.setUpdatedBy(getIdAuth());
				begin();
				itemsBrands = itemsBrandsDao.saveOrUpdate(itemsBrands);
				commit();
				updateItemsBrandsResDto.setVersion(itemsBrands.getVersion());
				updateItemsBrandsResDto.setMsg("OK");
			} catch (Exception e) {
				e.printStackTrace();
				rollback();
			}
			return updateItemsBrandsResDto;
		}
		throw new Exception("Access Denied");
	}

	@Override
	public ItemsBrands findById(String id) throws Exception {
		String permissionsCode = "PERMSN29";
		Boolean validation = validationUsers(permissionsCode);
		if (validation)
			return itemsBrandsDao.findById(id);
		throw new Exception("Access Denied");
	}

	@Override
	public List<ItemsBrands> findAll() throws Exception {
		String permissionsCode = "PERMSN29";
		Boolean validation = validationUsers(permissionsCode);
		if (validation)
			return itemsBrandsDao.findAll();
		throw new Exception("Access Denied");
	}

	@Override
	public ItemsBrands findByCode(String code) throws Exception {
		String permissionsCode = "PERMSN29";
		Boolean validation = validationUsers(permissionsCode);
		if (validation)
			return itemsBrandsDao.findByCode(code);
		throw new Exception("Access Denied");
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		String permissionsCode = "PERMSN32";
		Boolean validation = validationUsers(permissionsCode);
		if (validation) {
			try {
				begin();
				boolean isDeleted = itemsBrandsDao.removeById(id);
				commit();

				return isDeleted;
			} catch (Exception e) {
				e.printStackTrace();
				rollback();
				throw new Exception(e);
			}
		}throw new Exception("Access Denied");
	}

	public String generateCode() throws Exception {
		Integer increment = itemsBrandsDao.countData() + 1;
		String code = EnumCode.ITEMBRANDS.getCode() + increment;
		return code;
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

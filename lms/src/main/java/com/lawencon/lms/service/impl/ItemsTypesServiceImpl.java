package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.lawencon.lms.constant.EnumCode;
import com.lawencon.lms.dao.ItemsTypesDao;
import com.lawencon.lms.dao.PermissionsDao;
import com.lawencon.lms.dao.PermissionsRolesDao;
import com.lawencon.lms.dao.RolesDao;
import com.lawencon.lms.dao.UsersDao;
import com.lawencon.lms.dto.itemstypes.SaveItemsTypesResDto;
import com.lawencon.lms.dto.itemstypes.UpdateItemsTypesResDto;
import com.lawencon.lms.model.ItemsTypes;
import com.lawencon.lms.model.Permissions;
import com.lawencon.lms.model.PermissionsRoles;
import com.lawencon.lms.model.Roles;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.ItemsTypesService;
import com.lawencon.lms.service.UsersService;

@Service
public class ItemsTypesServiceImpl extends BaseServiceLmsImpl implements ItemsTypesService {

	@Autowired
	private ItemsTypesDao itemsTypesDao;

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RolesDao rolesDao;
	
	@Autowired
	private PermissionsDao permissionsDao;
	
	@Autowired
	private PermissionsRolesDao permissionRolesDao;
	
	@Autowired
	private UsersDao usersDao;

	@Override
	public List<ItemsTypes> findAll() throws Exception {
		String permissionCode = "PERMSN41";
		boolean validation = validation(permissionCode);		
		if(validation) {
			return itemsTypesDao.findAll();
		}
		else {
			throw new Exception("Access Denied");
		}
	}

	@Override
	public ItemsTypes findById(String id) throws Exception {
		String permissionCode = "PERMSN41";
		boolean validation = validation(permissionCode);		
		if(validation) {
		return itemsTypesDao.findById(id);
		}
		else {
			throw new Exception("Access Denied");
		}
	}

	@Override
	public ItemsTypes findByCode(String code) throws Exception {
		String permissionCode = "PERMSN41";
		boolean validation = validation(permissionCode);		
		if(validation) {
		return itemsTypesDao.findByCode(code);
		}
		else {
			throw new Exception("Access Denied");
		}
	}

	@Override
	public SaveItemsTypesResDto save(ItemsTypes itemsTypes) throws Exception {
		SaveItemsTypesResDto resDto = new SaveItemsTypesResDto();
		String permissionCode = "PERMSN42";
		boolean validation = validation(permissionCode);
		if(validation) {
			try {
				Users users = usersService.findById(getIdAuth());
				if (users == null) {
					throw new IllegalAccessException("must login first");
				} else if (!users.getRoles().getRolesName().equals("SUPER-ADMIN") && users.getIsActive() == false) {
					throw new IllegalAccessException("only superAdmin can Insert data!");
				} else {
					ItemsTypes itemType = new ItemsTypes();
					itemType.setCreatedBy(getIdAuth());
					itemType.setItemsTypesCode(countData());
					itemType.setItemsTypesName(itemsTypes.getItemsTypesName());
					begin();
					itemsTypes = itemsTypesDao.saveOrUpdate(itemType);
					commit();
					resDto.setId(itemsTypes.getId());
					resDto.setMessage("INSERTED");
				}

			} catch (Exception e) {
				e.printStackTrace();
				rollback();
			}
			return resDto;
		}
		else {
			throw new Exception("Access Denied");
		}
	}

	@Override
	public UpdateItemsTypesResDto update(ItemsTypes itemsTypes) throws Exception {
		UpdateItemsTypesResDto resDto = new UpdateItemsTypesResDto();
		String permissionCode = "PERMSN43";
		boolean validation = validation(permissionCode);
		if(validation) {
		try {
			ItemsTypes itemsType = itemsTypesDao.findByCode(itemsTypes.getItemsTypesCode());
			itemsType.setItemsTypesName(itemsTypes.getItemsTypesName());
			itemsType.setUpdatedBy(getIdAuth());
			begin();
			itemsTypes = itemsTypesDao.saveOrUpdate(itemsType);
			commit();
			resDto.setVersion(itemsTypes.getVersion());
			resDto.setMessage("UPDATED");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return resDto;
		}
		else {
			throw new Exception("Access Denied");
		}
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		String permissionCode = "PERMSN44";
		boolean validation = validation(permissionCode);
		if(validation) {
		try {
			begin();
			boolean delete = itemsTypesDao.removeById(id);
			commit();

			return delete;
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		}
		else {
			throw new Exception("Access Denied");
		}
	}

	@Override
	public String countData() throws Exception {
		int count = itemsTypesDao.count() + 1;
		String result = EnumCode.ITEMSTYPE.getCode() + count;
		return result;
	}
	
	public boolean validation(String permissionsCode)throws Exception{
		try {
			boolean check = false;
			Users users = usersDao.findById(getIdAuth());
			Roles roles = rolesDao.findById(users.getRoles().getId());
			Permissions permissions = permissionsDao.findByCode(permissionsCode);
			List<PermissionsRoles> listPermissionsRoles = permissionRolesDao.findAll();
			for (int i = 0; i < listPermissionsRoles.size(); i++) {
				if (listPermissionsRoles.get(i).getPermissions().getId().equals(permissions.getId())) {
					if (listPermissionsRoles.get(i).getRoles().getId().equals(roles.getId())) {
						check = true;
					}
				}
			}
			return check;
		} catch (NotFoundException e) {
			throw new Exception(e);
		}
	}
}

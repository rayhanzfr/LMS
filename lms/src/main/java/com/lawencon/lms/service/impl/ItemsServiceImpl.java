package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.lms.constant.EnumCode;
import com.lawencon.lms.dao.FilesDao;
import com.lawencon.lms.dao.ItemsDao;
import com.lawencon.lms.dao.PermissionsDao;
import com.lawencon.lms.dao.PermissionsRolesDao;
import com.lawencon.lms.dao.RolesDao;
import com.lawencon.lms.dao.UsersDao;
import com.lawencon.lms.dto.items.SaveItemsResDto;
import com.lawencon.lms.dto.items.UpdateItemsResDto;
import com.lawencon.lms.model.Files;
import com.lawencon.lms.model.Items;
import com.lawencon.lms.model.ItemsBrands;
import com.lawencon.lms.model.ItemsTypes;
import com.lawencon.lms.model.Permissions;
import com.lawencon.lms.model.PermissionsRoles;
import com.lawencon.lms.model.Roles;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.ItemsBrandsService;
import com.lawencon.lms.service.ItemsService;
import com.lawencon.lms.service.ItemsTypesService;

@Service
public class ItemsServiceImpl extends BaseServiceLmsImpl implements ItemsService {

	@Autowired
	private ItemsDao itemsDao;

	@Autowired
	private FilesDao filesDao;

	@Autowired
	private ItemsTypesService itemsTypesService;

	@Autowired
	private ItemsBrandsService itemsBrandsService;

	@Autowired
	private PermissionsDao permissionsDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private RolesDao rolesDao;

	@Autowired
	private PermissionsRolesDao permissionsRolesDao;

	@Override
	public SaveItemsResDto save(Items items, MultipartFile file) throws Exception {
		String permissionsCode = "PERMSN26";
		Boolean validation = validationUsers(permissionsCode);
		if (validation) {
			SaveItemsResDto saveItemsResDto = new SaveItemsResDto();
			try {
				String img = file.getOriginalFilename();
				String ext = img.substring(img.lastIndexOf(".") + 1, img.length());
				Files filesInsert = new Files();
				filesInsert.setFile(file.getBytes());
				filesInsert.setExtensions(ext);
				filesInsert.setCreatedBy(getIdAuth());
				begin();
				Files filesDb = new Files();
				filesDb = filesDao.saveOrUpdate(filesInsert);
				ItemsTypes itemsTypes = itemsTypesService.findById(items.getItemsTypes().getId());
				ItemsBrands itemsBrands = itemsBrandsService.findById(items.getItemsBrands().getId());
				items.setItemsCode(generateCode());
				items.setFiles(filesDb);
				items.setItemsTypes(itemsTypes);
				items.setItemsBrands(itemsBrands);
				items.setCreatedBy(getIdAuth());
				items = itemsDao.saveOrUpdate(items);
				commit();
				saveItemsResDto.setId(itemsBrands.getId());
				saveItemsResDto.setMsg("OK");
			} catch (Exception e) {
				e.printStackTrace();
				rollback();
			}
			return saveItemsResDto;
		}
		throw new Exception("Access Denied");
	}

	@Override
	public UpdateItemsResDto update(Items items, MultipartFile file) throws Exception {
		String permissionsCode = "PERMSN27";
		Boolean validation = validationUsers(permissionsCode);
		if (validation) {
			UpdateItemsResDto updateItemsResDto = new UpdateItemsResDto();
			try {
				String img = file.getOriginalFilename();
				String ext = img.substring(img.lastIndexOf(".") + 1, img.length());
				Files filesInsert = new Files();
				filesInsert.setFile(file.getBytes());
				filesInsert.setExtensions(ext);

				begin();
				Files files = filesDao.findById(items.getFiles().getId());
				files = filesDao.saveOrUpdate(files);
				ItemsTypes itemsTypes = itemsTypesService.findByCode(items.getItemsTypes().getItemsTypesCode());
				ItemsBrands itemsBrands = itemsBrandsService.findByCode(items.getItemsBrands().getItemsBrandsCode());
				items.setFiles(files);
				items.setItemsTypes(itemsTypes);
				items.setItemsBrands(itemsBrands);
				Items itemsDb = findByCode(items.getItemsCode());
				items.setCreatedAt(itemsDb.getCreatedAt());
				items.setCreatedBy(itemsDb.getCreatedBy());
				items.setUpdatedBy(getIdAuth());

				items = itemsDao.saveOrUpdate(items);
				commit();
				updateItemsResDto.setVersion(itemsBrands.getVersion());
				updateItemsResDto.setMsg("OK");
			} catch (Exception e) {
				e.printStackTrace();
				rollback();
			}
			return updateItemsResDto;
		}
		throw new Exception("Access Denied");
	}

	@Override
	public Items findById(String id) throws Exception {
		String permissionsCode = "PERMSN25";
		Boolean validation = validationUsers(permissionsCode);
		if (validation)
			return itemsDao.findById(id);
		throw new Exception("Access Denied");
	}

	@Override
	public List<Items> findAll() throws Exception {
		String permissionsCode = "PERMSN25";
		Boolean validation = validationUsers(permissionsCode);
		if (validation)
			return itemsDao.findAll();
		throw new Exception("Access Denied");
	}

	@Override
	public Items findByCode(String code) throws Exception {
		String permissionsCode = "PERMSN25";
		Boolean validation = validationUsers(permissionsCode);
		if (validation)
			return itemsDao.findByCode(code);
		throw new Exception("Access Denied");
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		String permissionsCode = "PERMSN25";
		Boolean validation = validationUsers(permissionsCode);
		if (validation) {
			try {
				begin();
				boolean isDeleted = itemsDao.removeById(id);
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
		Integer increment = itemsDao.countData() + 1;
		String code = EnumCode.ITEMS.getCode() + increment;
		return code;
	}

	public Boolean validationUsers(String permissionsCode) throws Exception {
		try {
			Users users = usersDao.findById(getIdAuth());
			Roles roles = rolesDao.findById(users.getRoles().getId());
			Permissions permissions = permissionsDao.findByCode(permissionsCode);
			List<PermissionsRoles> listPermissionsRoles = permissionsRolesDao.findAll();
			for (int i = 0; i < listPermissionsRoles.size(); i++) {
				if (listPermissionsRoles.get(i).getPermissions().getId() == permissions.getId()) {
					if (listPermissionsRoles.get(i).getRoles().getId() == roles.getId()) {
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

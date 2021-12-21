package com.lawencon.lms.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.lms.dao.PermissionsDao;
import com.lawencon.lms.dao.PermissionsRolesDao;
import com.lawencon.lms.dao.RolesDao;
import com.lawencon.lms.dao.StatusesInOutDao;
import com.lawencon.lms.dao.UsersDao;
import com.lawencon.lms.dto.statusesinout.SaveStatusesInOutResDto;
import com.lawencon.lms.dto.statusesinout.UpdateStatusesInOutResDto;
import com.lawencon.lms.model.Permissions;
import com.lawencon.lms.model.PermissionsRoles;
import com.lawencon.lms.model.Roles;
import com.lawencon.lms.model.StatusesInOut;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.StatusesInOutService;
import com.lawencon.lms.service.UsersService;

@Service
public class StatusesInOutServiceImpl extends BaseServiceLmsImpl implements StatusesInOutService {

	@Autowired
	private StatusesInOutDao statusesInOutDao;

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
	public List<StatusesInOut> findAll() throws Exception {
		String permissionCode = "PERMSN49";
		boolean validation = validation(permissionCode);
		if (validation) {
			return statusesInOutDao.findAll();
		} else {
			throw new Exception("Access Denied");
		}
	}

	@Override
	public StatusesInOut findById(String id) throws Exception {
		String permissionCode = "PERMSN49";
		boolean validation = validation(permissionCode);
		if (validation) {
			return statusesInOutDao.findById(id);
		} else {
			throw new Exception("Access Denied");
		}
	}

	@Override
	public StatusesInOut findByCode(String code) throws Exception {
		String permissionCode = "PERMSN49";
		boolean validation = validation(permissionCode);
		if (validation) {
			return statusesInOutDao.findByCode(code);
		} else {
			throw new Exception("Access Denied");
		}
	}

	@Override
	public SaveStatusesInOutResDto save(StatusesInOut statusesInOut) throws Exception {
		SaveStatusesInOutResDto resDto = new SaveStatusesInOutResDto();
		String permissionCode = "PERMSN50";
		boolean validation = validation(permissionCode);
		if (validation) {
			try {
				begin();
				statusesInOut = statusesInOutDao.saveOrUpdate(statusesInOut);
				commit();
				resDto.setId(statusesInOut.getId());
				resDto.setMesssage("INSERTED");
			} catch (Exception e) {
				e.printStackTrace();
				rollback();
			}
			return resDto;
		} else {
			throw new Exception("Access Denied");
		}
	}

	@Override
	public UpdateStatusesInOutResDto update(StatusesInOut statusesInOut) throws Exception {
		String permissionCode = "PERMSN51";
		boolean validation = validation(permissionCode);
		if (validation) {
			UpdateStatusesInOutResDto resDto = new UpdateStatusesInOutResDto();
			try {
				StatusesInOut statusesInAndOut = statusesInOutDao.findByCode(statusesInOut.getStatusesInOutCode());
				statusesInAndOut.setUpdatedBy(getIdAuth());
				statusesInAndOut.setStatusesInOutName(statusesInOut.getStatusesInOutName());

				begin();
				statusesInOut = statusesInOutDao.saveOrUpdate(statusesInAndOut);
				commit();
				resDto.setVersion(statusesInOut.getVersion());
				resDto.setMessage("UPDATED");
			} catch (Exception e) {
				e.printStackTrace();
				rollback();
			}
			return resDto;
		} else {
			throw new Exception("Access Denied");
		}
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		String permissionCode = "PERMSN52";
		boolean validation = validation(permissionCode);
		if (validation) {
			try {
				begin();
				boolean delete = statusesInOutDao.removeById(id);
				commit();

				return delete;
			} catch (Exception e) {
				e.printStackTrace();
				rollback();
				throw new Exception(e);
			}
		} else {
			throw new Exception("Access Denied");
		}
	}

	public boolean validation(String permissionsCode) throws Exception {
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

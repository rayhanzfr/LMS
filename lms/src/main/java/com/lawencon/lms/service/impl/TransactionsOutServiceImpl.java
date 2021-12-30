package com.lawencon.lms.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.lawencon.lms.constant.EnumCode;
import com.lawencon.lms.constant.StatusesAssetsCode;
import com.lawencon.lms.constant.StatusesInOutCode;
import com.lawencon.lms.dao.AssetsDao;
import com.lawencon.lms.dao.EmployeesDao;
import com.lawencon.lms.dao.HistoriesDao;
import com.lawencon.lms.dao.LocationsDao;
import com.lawencon.lms.dao.PermissionsDao;
import com.lawencon.lms.dao.PermissionsRolesDao;
import com.lawencon.lms.dao.RolesDao;
import com.lawencon.lms.dao.StatusesAssetsDao;
import com.lawencon.lms.dao.StatusesInOutDao;
import com.lawencon.lms.dao.TransactionsDetailOutDao;
import com.lawencon.lms.dao.TransactionsOutDao;
import com.lawencon.lms.dao.UsersDao;
import com.lawencon.lms.dto.transactionsout.GetAllTransactionsOutByUsersResDto;
import com.lawencon.lms.dto.transactionsout.GetAllTransactionsOutResDto;
import com.lawencon.lms.dto.transactionsout.GetByTransactionsOutCodeResDto;
import com.lawencon.lms.dto.transactionsout.GetByTransactionsOutIdResDto;
import com.lawencon.lms.dto.transactionsout.GetTransactionsOutDataDto;
import com.lawencon.lms.dto.transactionsout.SaveFullTransactionsOutReqDto;
import com.lawencon.lms.dto.transactionsout.SaveFullTransactionsOutResDto;
import com.lawencon.lms.dto.transactionsout.SaveTransactionsDetailsOutReqDto;
import com.lawencon.lms.dto.transactionsout.SaveTransactionsDetailsOutResDto;
import com.lawencon.lms.dto.transactionsout.SaveTransactionsOutReqDto;
import com.lawencon.lms.dto.transactionsout.SaveTransactionsOutResDto;
import com.lawencon.lms.model.Assets;
import com.lawencon.lms.model.Employees;
import com.lawencon.lms.model.Histories;
import com.lawencon.lms.model.Locations;
import com.lawencon.lms.model.Permissions;
import com.lawencon.lms.model.PermissionsRoles;
import com.lawencon.lms.model.Roles;
import com.lawencon.lms.model.StatusesAssets;
import com.lawencon.lms.model.StatusesInOut;
import com.lawencon.lms.model.TransactionsDetailOut;
import com.lawencon.lms.model.TransactionsOut;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.TransactionsOutService;

@Service
public class TransactionsOutServiceImpl extends BaseServiceLmsImpl implements TransactionsOutService {

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private HistoriesDao historiesDao;

	@Autowired
	private TransactionsOutDao transactionsOutDao;

	@Autowired
	private LocationsDao locationsDao;

	@Autowired
	private EmployeesDao employeesDao;

	@Autowired
	private AssetsDao assetsDao;

	@Autowired
	private StatusesInOutDao statusesInOutDao;

	@Autowired
	private StatusesAssetsDao statusesAssetsDao;

	@Autowired
	private TransactionsDetailOutDao transactionsDetailOutDao;

	@Autowired
	private PermissionsDao permissionsDao;

	@Autowired
	private RolesDao rolesDao;

	@Autowired
	private PermissionsRolesDao permissionsRolesDao;

	@Override
	public SaveFullTransactionsOutResDto save(SaveFullTransactionsOutReqDto itemsReq) throws Exception {
		String permissionsCode = "PERMSN34";
		Boolean validation = validationUsers(permissionsCode);
		if (validation) {
			SaveFullTransactionsOutResDto saveFullTransactionsOutResDto = new SaveFullTransactionsOutResDto();
			SaveTransactionsOutResDto headerRes = new SaveTransactionsOutResDto();
			SaveFullTransactionsOutReqDto saveFullTransactionsOutReqDto = (SaveFullTransactionsOutReqDto) itemsReq;
			SaveTransactionsOutReqDto saveTransactionsOutReqDto = saveFullTransactionsOutReqDto
					.getSaveTransactionsOutReqDto();
			List<SaveTransactionsDetailsOutReqDto> listSaveTransactionsDetailsOutReqDto = saveFullTransactionsOutReqDto
					.getListSaveTransactionsDetailsOutReqDto();
			List<SaveTransactionsDetailsOutResDto> detailsRes = new ArrayList<>();
			TransactionsOut transactionsOut = new TransactionsOut();
			try {

				String code = generateCode();
				saveTransactionsOutReqDto.setTransactionsOutCode(code);
				transactionsOut.setTransactionsOutCode(saveTransactionsOutReqDto.getTransactionsOutCode());
				transactionsOut.setCheckOutDate(LocalDate.now());
				transactionsOut.setCreatedBy(getIdAuth());
				begin();
				final TransactionsOut transactionsOutFinal = transactionsOutDao.saveOrUpdate(transactionsOut);
				commit();
				listSaveTransactionsDetailsOutReqDto.forEach(i -> {
					SaveTransactionsDetailsOutResDto detail = new SaveTransactionsDetailsOutResDto();
					TransactionsDetailOut transactionsDetailOut = new TransactionsDetailOut();
					transactionsDetailOut.setTransactionsOut(transactionsOutFinal);
					if (i.getLocationsCode() != null && i.getEmployeesCode() == null && i.getAssetsName() != null) {
						Locations locations = new Locations();
						try {
							Locations locationsDb = locationsDao.findByCode(i.getLocationsCode());
							locations.setId(locationsDb.getId());
							locations.setLocationsCode(locationsDb.getLocationsCode());
							transactionsDetailOut.setLocations(locations);
						} catch (Exception e) {
							e.printStackTrace();
							rollback();
						}
					} else if (i.getLocationsCode() == null && i.getEmployeesCode() != null
							&& i.getAssetsName() != null) {

						Employees employees = new Employees();
						try {
							Employees employeesDb = employeesDao.findByCode(i.getEmployeesCode());
							employees.setId(employeesDb.getId());
							employees.setEmployeesCode(employees.getEmployeesCode());
							transactionsDetailOut.setEmployees(employees);
						} catch (Exception e) {
							e.printStackTrace();
							rollback();
						}
					}

					Assets assets = new Assets();
					try {
						Assets assetsDb = assetsDao.findByAssetsName(i.getAssetsName());
						assets = assetsDb;
						transactionsDetailOut.setAssets(assets);
					} catch (Exception e) {
						e.printStackTrace();
					}

					transactionsDetailOut.setTransactionDetailOutExpired(LocalDate.parse(i.getExpiredDate()));
					try {
						transactionsDetailOut.setCreatedBy(getIdAuth());
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						begin();
						transactionsDetailOut = transactionsDetailOutDao.saveOrUpdate(transactionsDetailOut);
						StatusesInOut statusesInOut = new StatusesInOut();
						statusesInOut = statusesInOutDao.findByCode(StatusesInOutCode.CHECKOUT.getCode());
						StatusesAssets statusesAssets = new StatusesAssets();
						statusesAssets = statusesAssetsDao.findByCode(StatusesAssetsCode.UNDEPLOYABLE.getCode());
						Assets assetsUpdate = transactionsDetailOut.getAssets();
						assetsUpdate.setStatusesInOut(statusesInOut);
						assetsUpdate.setStatusesAssets(statusesAssets);
						transactionsDetailOut.setAssets(assetsDao.saveOrUpdate(assetsUpdate));
						detail.setId(transactionsDetailOut.getId());
						detailsRes.add(detail);
						commit();
					} catch (Exception e) {
						e.printStackTrace();
						rollback();
					}
					Histories histories = new Histories();
					Users users = new Users();
					try {
						users = usersDao.findById(getIdAuth());
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						histories.setAssets(transactionsDetailOut.getAssets());
						histories.setUsers(users);
						histories.setActivityName(StatusesInOutCode.CHECKOUT.getCode());
						histories.setCreatedBy(getIdAuth());
						begin();
						histories = historiesDao.saveOrUpdate(histories);
						commit();
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				headerRes.setId(transactionsOut.getId());
				headerRes.setListDetail(detailsRes);
				saveFullTransactionsOutResDto.setSaveTransactionsOutResDto(headerRes);
				saveFullTransactionsOutResDto.setMessage("SUCCESS");

			} catch (Exception e) {
				e.printStackTrace();
				rollback();
			}
			return saveFullTransactionsOutResDto;
		}
		throw new Exception("Access Denied");
	}

	@Override
	public GetByTransactionsOutIdResDto findById(String id) throws Exception {
		String permissionsCode = "PERMSN33";
		Boolean validation = validationUsers(permissionsCode);
		if (validation) {

			GetByTransactionsOutIdResDto headerRes = new GetByTransactionsOutIdResDto();
			GetTransactionsOutDataDto header = new GetTransactionsOutDataDto();
			TransactionsOut headerDb = transactionsOutDao.findById(id);
			header.setTransactionsOutCode(headerDb.getTransactionsOutCode());
			header.setCheckOutDate(headerDb.getCheckOutDate());
			header.setVersion(headerDb.getVersion());
			header.setCreatedBy(headerDb.getCreatedBy());
			header.setCreatedAt(headerDb.getCreatedAt());
			header.setUpdatedBy(headerDb.getUpdatedBy());
			header.setUpdatedAt(headerDb.getUpdatedAt());
			headerRes.setGetTransactionsOutDataDto(header);
			headerRes.setMessage("SUCCESS");
			return headerRes;
		}
		throw new Exception("Access Denied");
	}

	@Override
	public GetByTransactionsOutCodeResDto findByCode(String code) throws Exception {
		String permissionsCode = "PERMSN33";
		Boolean validation = validationUsers(permissionsCode);
		if (validation) {

			GetByTransactionsOutCodeResDto headerRes = new GetByTransactionsOutCodeResDto();
			GetTransactionsOutDataDto header = new GetTransactionsOutDataDto();
			TransactionsOut headerDb = transactionsOutDao.findByCode(code);
			header.setTransactionsOutCode(headerDb.getTransactionsOutCode());
			header.setCheckOutDate(headerDb.getCheckOutDate());
			header.setVersion(headerDb.getVersion());
			header.setCreatedBy(headerDb.getCreatedBy());
			header.setCreatedAt(headerDb.getCreatedAt());
			header.setUpdatedBy(headerDb.getUpdatedBy());
			header.setUpdatedAt(headerDb.getUpdatedAt());
			headerRes.setData(header);
			headerRes.setMessage("SUCCESS");
			return headerRes;
		}
		throw new Exception("Access Denied");
	}

	@Override
	public GetAllTransactionsOutResDto findAll() throws Exception {
		String permissionsCode = "PERMSN33";
		Boolean validation = validationUsers(permissionsCode);
		if (validation) {

			GetAllTransactionsOutResDto headerRes = new GetAllTransactionsOutResDto();
			List<GetTransactionsOutDataDto> listHeader = new ArrayList<>();
			List<TransactionsOut> listHeaderDb = transactionsOutDao.findAll();
			listHeaderDb.forEach(i -> {
				GetTransactionsOutDataDto header = new GetTransactionsOutDataDto();
				header.setTransactionsOutCode(i.getTransactionsOutCode());
				header.setCheckOutDate(i.getCheckOutDate());
				header.setVersion(i.getVersion());
				header.setCreatedBy(i.getCreatedBy());
				header.setCreatedAt(i.getCreatedAt());
				header.setUpdatedBy(i.getUpdatedBy());
				header.setUpdatedAt(i.getUpdatedAt());
				listHeader.add(header);
			});
			headerRes.setGetTransactionsOutDataDto(listHeader);
			headerRes.setMessage("SUCCESS");
			return headerRes;
		}
		throw new Exception("Access Denied");
	}

	public String generateCode() throws Exception {
		Integer increment = transactionsOutDao.countData() + 1;
		String code = EnumCode.TRANSACTIONSOUT.getCode() + increment;
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

	@Override
	public GetAllTransactionsOutByUsersResDto findAllByUsers() throws Exception {
		String permissionsCode = "PERMSN33";
		Boolean validation = validationUsers(permissionsCode);
		if (validation) {

			GetAllTransactionsOutByUsersResDto headerRes = new GetAllTransactionsOutByUsersResDto();
			List<GetTransactionsOutDataDto> listHeader = new ArrayList<>();
			List<TransactionsOut> listHeaderDb = transactionsOutDao.findAll();
			listHeaderDb.forEach(i -> {
				GetTransactionsOutDataDto header = new GetTransactionsOutDataDto();
				try {
					String usersId = getIdAuth();
					if (usersId == header.getCreatedBy()) {
						header.setTransactionsOutCode(i.getTransactionsOutCode());
						header.setCheckOutDate(i.getCheckOutDate());
						header.setVersion(i.getVersion());
						header.setCreatedBy(i.getCreatedBy());
						header.setCreatedAt(i.getCreatedAt());
						header.setUpdatedBy(i.getUpdatedBy());
						header.setUpdatedAt(i.getUpdatedAt());
						listHeader.add(header);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			headerRes.setGetTransactionsOutDataDto(listHeader);
			headerRes.setMessage("SUCCESS");
			return headerRes;
		}
		throw new Exception("Access Denied");
	}

}

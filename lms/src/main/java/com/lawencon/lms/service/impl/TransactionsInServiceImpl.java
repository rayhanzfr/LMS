package com.lawencon.lms.service.impl;

import java.time.LocalDateTime;
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
import com.lawencon.lms.dao.StatusesTransactionsDao;
import com.lawencon.lms.dao.TransactionsDetailInDao;
import com.lawencon.lms.dao.TransactionsInDao;
import com.lawencon.lms.dao.UsersDao;
import com.lawencon.lms.dto.transactionsin.GetAllTransactionsInByUsersResDto;
import com.lawencon.lms.dto.transactionsin.GetAllTransactionsInResDto;
import com.lawencon.lms.dto.transactionsin.GetByTransactionsInCodeResDto;
import com.lawencon.lms.dto.transactionsin.GetByTransactionsInIdResDto;
import com.lawencon.lms.dto.transactionsin.GetTransactionsInDataDto;
import com.lawencon.lms.dto.transactionsin.SaveFullTransactionsInReqDto;
import com.lawencon.lms.dto.transactionsin.SaveFullTransactionsInResDto;
import com.lawencon.lms.dto.transactionsin.SaveTransactionsDetailsInReqDto;
import com.lawencon.lms.dto.transactionsin.SaveTransactionsDetailsInResDto;
import com.lawencon.lms.dto.transactionsin.SaveTransactionsInResDto;
import com.lawencon.lms.dto.transactionsout.GetAllTransactionsOutByUsersResDto;
import com.lawencon.lms.dto.transactionsout.GetTransactionsOutDataDto;
import com.lawencon.lms.model.Assets;
import com.lawencon.lms.model.Employees;
import com.lawencon.lms.model.Histories;
import com.lawencon.lms.model.Locations;
import com.lawencon.lms.model.Permissions;
import com.lawencon.lms.model.PermissionsRoles;
import com.lawencon.lms.model.Roles;
import com.lawencon.lms.model.StatusesAssets;
import com.lawencon.lms.model.StatusesInOut;
import com.lawencon.lms.model.StatusesTransactions;
import com.lawencon.lms.model.TransactionsDetailIn;
import com.lawencon.lms.model.TransactionsIn;
import com.lawencon.lms.model.TransactionsOut;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.TransactionsInService;

@Service
public class TransactionsInServiceImpl extends BaseServiceLmsImpl implements TransactionsInService {

	@Autowired
	private TransactionsInDao transactionsInDao;

	@Autowired
	private LocationsDao locationsDao;

	@Autowired
	private EmployeesDao employeesDao;

	@Autowired
	private AssetsDao assetsDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private HistoriesDao historiesDao;

	@Autowired
	private StatusesTransactionsDao statusesTransactionsDao;

	@Autowired
	private StatusesInOutDao statusesInOutDao;

	@Autowired
	private StatusesAssetsDao statusesAssetsDao;

	@Autowired
	private TransactionsDetailInDao transactionsDetailInDao;

	@Autowired
	private PermissionsDao permissionsDao;

	@Autowired
	private RolesDao rolesDao;

	@Autowired
	private PermissionsRolesDao permissionsRolesDao;

	@Override
	public GetAllTransactionsInResDto findAll() throws Exception {

		String permissionsCode = "PERMSN35";
		Boolean validation = validationUsers(permissionsCode);
		if (validation) {
			GetAllTransactionsInResDto results = new GetAllTransactionsInResDto();
			List<GetTransactionsInDataDto> listDto = new ArrayList<>();
			List<TransactionsIn> listIn = transactionsInDao.findAll();

			listIn.forEach(ld -> {
				GetTransactionsInDataDto dataDto = new GetTransactionsInDataDto();
				dataDto.setId(ld.getId());
				dataDto.setTransactionsInCode(ld.getTransactionsInCode());
				dataDto.setTransactionsInDate(ld.getTransactionsInDate().toString());
				dataDto.setTransactionsOutId(ld.getTransactionsOut().getId());

				dataDto.setVersion(ld.getVersion());
				dataDto.setCreatedAt(ld.getCreatedAt());
				dataDto.setCreatedBy(ld.getCreatedBy());
				dataDto.setUpdatedAt(ld.getUpdatedAt());
				dataDto.setUpdatedBy(ld.getUpdatedBy());
				dataDto.setIsActive(ld.getIsActive());
				listDto.add(dataDto);
			});

			results.setGetTransactionsInDataDto(listDto);
			results.setMessage("SUCCESS");
			return results;

		}
		throw new Exception("Access Denied");

	}

	@Override
	public GetByTransactionsInIdResDto findById(String id) throws Exception {
		String permissionsCode = "PERMSN35";
		Boolean validation = validationUsers(permissionsCode);
		if (validation) {
			GetByTransactionsInIdResDto findByIdTransactionInDto = new GetByTransactionsInIdResDto();
			TransactionsIn tin = transactionsInDao.findById(id);
			GetTransactionsInDataDto tinDataDto = new GetTransactionsInDataDto();
			tinDataDto.setId(tin.getId());
			tinDataDto.setTransactionsInCode(tin.getTransactionsInCode());
			tinDataDto.setTransactionsInDate(tin.getTransactionsInDate().toString());
			tinDataDto.setTransactionsOutId(tin.getTransactionsOut().getId());
			tinDataDto.setVersion(tin.getVersion());
			tinDataDto.setCreatedAt(tin.getCreatedAt());
			tinDataDto.setCreatedBy(tin.getCreatedBy());
			tinDataDto.setUpdatedAt(tin.getUpdatedAt());
			tinDataDto.setUpdatedBy(tin.getUpdatedBy());
			tinDataDto.setIsActive(tin.getIsActive());

			findByIdTransactionInDto.setData(tinDataDto);
			findByIdTransactionInDto.setMessage("Sukses");

			return findByIdTransactionInDto;

		}
		throw new Exception("Access Denied");

	}

	@Override
	public GetByTransactionsInCodeResDto findByCode(String code) throws Exception {
		String permissionsCode = "PERMSN35";
		Boolean validation = validationUsers(permissionsCode);
		if (validation) {
			GetByTransactionsInCodeResDto findByCodeTransactionInDto = new GetByTransactionsInCodeResDto();
			TransactionsIn tin = transactionsInDao.findByCode(code);
			GetTransactionsInDataDto tinDataDto = new GetTransactionsInDataDto();
			tinDataDto.setId(tin.getId());
			tinDataDto.setTransactionsInCode(tin.getTransactionsInCode());
			tinDataDto.setTransactionsInDate(tin.getTransactionsInDate().toString());
			tinDataDto.setTransactionsOutId(tin.getTransactionsOut().getId());
			tinDataDto.setVersion(tin.getVersion());
			tinDataDto.setCreatedAt(tin.getCreatedAt());
			tinDataDto.setCreatedBy(tin.getCreatedBy());
			tinDataDto.setUpdatedAt(tin.getUpdatedAt());
			tinDataDto.setUpdatedBy(tin.getUpdatedBy());
			tinDataDto.setIsActive(tin.getIsActive());

			findByCodeTransactionInDto.setData(tinDataDto);
			findByCodeTransactionInDto.setMessage("Sukses");

			return findByCodeTransactionInDto;

		}
		throw new Exception("Access Denied");
	}

	@Override
	public SaveFullTransactionsInResDto save(SaveFullTransactionsInReqDto saveFullReq) throws Exception {
		SaveFullTransactionsInResDto saveFullResDto = new SaveFullTransactionsInResDto();
		SaveTransactionsInResDto saveResDto = new SaveTransactionsInResDto();
		List<SaveTransactionsDetailsInResDto> detailsRes = new ArrayList<>();
		TransactionsIn transactionsIn = new TransactionsIn();

		try {
			String permissionsCode = "PERMSN46";
			Boolean validation = validationUsers(permissionsCode);
			if (!validation) {
				throw new Exception("Access Denied");
			}

			else {
				begin();
				transactionsIn.setTransactionsInCode(generateCode());
				transactionsIn.setTransactionsInDate(LocalDateTime.now());

				TransactionsIn tin = transactionsInDao.saveOrUpdate(transactionsIn);

				for (SaveTransactionsDetailsInReqDto saveDet : saveFullReq.getTransactionsDetailDto()) {
					SaveTransactionsDetailsInResDto detail = new SaveTransactionsDetailsInResDto();
					TransactionsDetailIn tdin = new TransactionsDetailIn();

					if (saveDet.getLocationsCode() == null) {
						throw new Exception("locationsCode required");
					}
					
					else if(saveDet.getEmployeesCode() == null) {
						throw new Exception("employeesCode required");
					}

					else if(saveDet.getAssetsName() == null) {
						throw new Exception("assetsName required");
					}
					
					else if(saveDet.getStatusesTransactionsCode() == null) {
						throw new Exception("statusesTransactions required");
					}
					
					else {
						Locations location = locationsDao.findByCode(saveDet.getLocationsCode());
						Employees employees = employeesDao.findByCode(saveDet.getEmployeesCode());
						Assets assets = assetsDao.findByAssetsName(saveDet.getAssetsName());
						StatusesTransactions statusesTransactions = statusesTransactionsDao
								.findByCode(saveDet.getStatusesTransactionsCode());

						tdin.setTransactionsIn(tin);
						tdin.setLocations(location);
						tdin.setEmployees(employees);
						tdin.setAssets(assets);
						tdin.setStatusesTransactions(statusesTransactions);
						tdin.setReturnDate(LocalDateTime.now());

						transactionsDetailInDao.saveOrUpdate(tdin);

						StatusesInOut statusesInOut = new StatusesInOut();
						statusesInOut = statusesInOutDao.findByCode(StatusesInOutCode.CHECKOUT.getCode());
						StatusesAssets statusesAssets = new StatusesAssets();
						statusesAssets = statusesAssetsDao.findByCode(StatusesAssetsCode.UNDEPLOYABLE.getCode());
						Assets assetsUpdate = tdin.getAssets();
						assetsUpdate.setStatusesInOut(statusesInOut);
						assetsUpdate.setStatusesAssets(statusesAssets);
						tdin.setAssets(assetsDao.saveOrUpdate(assetsUpdate));
						detail.setId(tdin.getId());
						detailsRes.add(detail);

						Histories histories = new Histories();
						Users users = new Users();
						users = usersDao.findById(getIdAuth());
						histories.setAssets(tdin.getAssets());
						histories.setUsers(users);
						histories.setActivityName(StatusesInOutCode.CHECKIN.getCode());
						histories = historiesDao.saveOrUpdate(histories);
					}

				}
				saveResDto.setListDetail(detailsRes);
				saveFullResDto.setSaveTransactionsInResDto(saveResDto);
				saveFullResDto.setMessage("Sukses");
				commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}

		return saveFullResDto;
	}

	@Override
	public GetAllTransactionsInByUsersResDto findAllByUsers() throws Exception {
		String permissionsCode = "PERMSN33";
		Boolean validation = validationUsers(permissionsCode);
		if (validation) {

			GetAllTransactionsInByUsersResDto headerRes = new GetAllTransactionsInByUsersResDto();
			List<GetTransactionsInDataDto> listHeader = new ArrayList<>();
			List<TransactionsIn> listHeaderDb = transactionsInDao.findAll();
			listHeaderDb.forEach(i -> {
				GetTransactionsInDataDto header = new GetTransactionsInDataDto();
				try {
					String usersId = getIdAuth();
					if (usersId == header.getCreatedBy()) {
						header.setTransactionsInCode(i.getTransactionsInCode());
						header.setTransactionsInDate(i.getTransactionsInDate().toString());
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
			headerRes.setGetTransactionsInDataDto(listHeader);
			headerRes.setMessage("SUCCESS");
			return headerRes;
		}
		throw new Exception("Access Denied");
	}
	
	public String generateCode() throws Exception {
		String generatedCode = EnumCode.TRANSACTIONSIN.getCode() + (transactionsInDao.countData() + 1);
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

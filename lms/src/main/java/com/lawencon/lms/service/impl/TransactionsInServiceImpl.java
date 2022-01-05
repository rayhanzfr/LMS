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
import com.lawencon.lms.constant.StatusesTransactionsCode;
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
import com.lawencon.lms.dao.TransactionsOutDao;
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
	private TransactionsOutDao transactionsOutDao;

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
	
	public String companiesCode()throws Exception{
		Employees employees = employeesDao.findByUserId(getIdAuth());
		String companiesCode = employees.getCompanies().getCompaniesCode();
		return companiesCode;
	}

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
			String permissionsCode = "PERMSN36";
			Boolean validation = validationUsers(permissionsCode);
			if (!validation) {
				throw new Exception("Access Denied");
			}

			else {
				begin();
				TransactionsOut transactionsOut = transactionsOutDao
						.findByCode(saveFullReq.getSaveTransactionsInReqDto().getTransactionsOutCode());
				transactionsIn.setTransactionsOut(transactionsOut);
				transactionsIn.setTransactionsInCode(generateCode());
				transactionsIn.setTransactionsInDate(LocalDateTime.now());
				transactionsIn.setCreatedBy(getIdAuth());

				TransactionsIn tin = transactionsInDao.saveOrUpdate(transactionsIn);

				for (SaveTransactionsDetailsInReqDto saveDet : saveFullReq.getTransactionsDetailDto()) {
					SaveTransactionsDetailsInResDto detail = new SaveTransactionsDetailsInResDto();
					TransactionsDetailIn tdin = new TransactionsDetailIn();

					if (saveDet.getLocationsCode() != null && saveDet.getEmployeesCode() == null
							&& saveDet.getAssetsName() != null) {
						Locations locations = new Locations();
						try {
							Locations locationsDb = locationsDao.findByCode(saveDet.getLocationsCode());
							locations.setId(locationsDb.getId());
							locations.setLocationsCode(locationsDb.getLocationsCode());
							tdin.setLocations(locations);
						} catch (Exception e) {
							e.printStackTrace();
							rollback();
						}
					} else if (saveDet.getLocationsCode() == null && saveDet.getEmployeesCode() != null
							&& saveDet.getAssetsName() != null) {

						Employees employees = new Employees();
						try {
							Employees employeesDb = employeesDao.findByCode(saveDet.getEmployeesCode());
							employees.setId(employeesDb.getId());
							employees.setEmployeesCode(employees.getEmployeesCode());
							tdin.setEmployees(employees);
						} catch (Exception e) {
							e.printStackTrace();
							rollback();
						}
					} else if (saveDet.getAssetsName() == null) {
						throw new Exception("assetsName required");
					}

					else if (saveDet.getStatusesTransactionsCode() == null) {
						throw new Exception("statusesTransactions required");
					}

					
					Assets assets = assetsDao.findByAssetsName(companiesCode(),saveDet.getAssetsName());
					StatusesTransactions statusesTransactions = statusesTransactionsDao.findByCode(saveDet.getStatusesTransactionsCode());

					tdin.setTransactionsIn(tin);
					tdin.setAssets(assets);
					tdin.setStatusesTransactions(statusesTransactions);
					tdin.setReturnDate(LocalDateTime.now());
					tdin.setCreatedBy(getIdAuth());

					transactionsDetailInDao.saveOrUpdate(tdin);
					Assets assetsUpdate = tdin.getAssets();
					StatusesAssets statusesAssets = new StatusesAssets();
					if(statusesTransactions.getStatusesTransactionsCode().equals(StatusesTransactionsCode.READY.getCode())) {
						statusesAssets = statusesAssetsDao.findByCode(StatusesAssetsCode.DEPLOYABLE.getCode());
						assetsUpdate.setStatusesAssets(statusesAssets);
					}else if(statusesTransactions.getStatusesTransactionsCode().equals(StatusesTransactionsCode.LOSTORSTOLEN.getCode())) {
						statusesAssets = statusesAssetsDao.findByCode(StatusesAssetsCode.ARCHIVED.getCode());
						assetsUpdate.setStatusesAssets(statusesAssets);
					}else if(statusesTransactions.getStatusesTransactionsCode().equals(StatusesTransactionsCode.BROKEN.getCode())) {
						statusesAssets = statusesAssetsDao.findByCode(StatusesAssetsCode.UNDEPLOYABLE.getCode());
						assetsUpdate.setStatusesAssets(statusesAssets);
					}else if(statusesTransactions.getStatusesTransactionsCode().equals(StatusesTransactionsCode.REPAIR.getCode())) {
						statusesAssets = statusesAssetsDao.findByCode(StatusesAssetsCode.UNDEPLOYABLE.getCode());
						assetsUpdate.setStatusesAssets(statusesAssets);
					}
					StatusesInOut statusesInOut = new StatusesInOut();
					statusesInOut = statusesInOutDao.findByCode(StatusesInOutCode.CHECKIN.getCode());
					assetsUpdate.setStatusesInOut(statusesInOut);
					tdin.setAssets(assetsDao.saveOrUpdate(assetsUpdate));
					detail.setId(tdin.getId());
					detailsRes.add(detail);

					Histories histories = new Histories();
					Users users = new Users();
					users = usersDao.findById(getIdAuth());
					histories.setAssets(tdin.getAssets());
					histories.setUsers(users);
					histories.setActivityName(StatusesInOutCode.CHECKIN.name());
					histories.setCreatedBy(getIdAuth());
					histories = historiesDao.saveOrUpdate(histories);
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
					if (usersId.equals(i.getCreatedBy())) {
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

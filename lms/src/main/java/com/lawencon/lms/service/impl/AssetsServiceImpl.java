package com.lawencon.lms.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.lms.assets.ExcelRequest;
import com.lawencon.lms.constant.RolesCode;
import com.lawencon.lms.constant.StatusesInOutCode;
import com.lawencon.lms.dao.AssetsDao;
import com.lawencon.lms.dao.CompaniesDao;
import com.lawencon.lms.dao.EmployeesDao;
import com.lawencon.lms.dao.HistoriesDao;
import com.lawencon.lms.dao.InvoicesDao;
import com.lawencon.lms.dao.ItemsDao;
import com.lawencon.lms.dao.ItemsTypesDao;
import com.lawencon.lms.dao.PermissionsDao;
import com.lawencon.lms.dao.PermissionsRolesDao;
import com.lawencon.lms.dao.RolesDao;
import com.lawencon.lms.dao.StatusesAssetsDao;
import com.lawencon.lms.dao.StatusesInOutDao;
import com.lawencon.lms.dao.UsersDao;
import com.lawencon.lms.dto.assets.AssetsDataDto;
import com.lawencon.lms.dto.assets.GetAllAssetsDto;
import com.lawencon.lms.dto.assets.GetByIdAssetsDto;
import com.lawencon.lms.dto.assets.GetTotalAssetsReqDto;
import com.lawencon.lms.dto.assets.SaveAssetsDataDto;
import com.lawencon.lms.dto.assets.SaveAssetsReqDto;
import com.lawencon.lms.dto.assets.SaveAssetsResDto;
import com.lawencon.lms.dto.assets.UpdateAssetsDataDto;
import com.lawencon.lms.dto.assets.UpdateAssetsReqDto;
import com.lawencon.lms.dto.assets.UpdateAssetsResDto;
import com.lawencon.lms.email.EmailHelper;
import com.lawencon.lms.model.Assets;
import com.lawencon.lms.model.Companies;
import com.lawencon.lms.model.Employees;
import com.lawencon.lms.model.Histories;
import com.lawencon.lms.model.Invoices;
import com.lawencon.lms.model.Items;
import com.lawencon.lms.model.ItemsTypes;
import com.lawencon.lms.model.JasperAssets;
import com.lawencon.lms.model.Permissions;
import com.lawencon.lms.model.PermissionsRoles;
import com.lawencon.lms.model.Roles;
import com.lawencon.lms.model.StatusesAssets;
import com.lawencon.lms.model.StatusesInOut;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.AssetsService;

@Service
public class AssetsServiceImpl extends BaseServiceLmsImpl implements AssetsService {

	@Autowired
	private AssetsDao assetsDao;

	@Autowired
	private ItemsDao itemsDao;

	@Autowired
	private ItemsTypesDao itemsTypesDao;

	@Autowired
	private StatusesAssetsDao statusesAssetsDao;

	@Autowired
	private StatusesInOutDao statusesInOutDao;

	@Autowired
	private InvoicesDao invoicesDao;

	@Autowired
	private RolesDao rolesDao;

	@Autowired
	private PermissionsDao permissionsDao;

	@Autowired
	private PermissionsRolesDao permissionRolesDao;

	@Autowired
	private HistoriesDao historiesDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private CompaniesDao companiesDao;

	@Autowired
	private EmployeesDao employeesDao;

	private AssetsDataDto convert(Assets assets) {
		AssetsDataDto data = new AssetsDataDto();
		data.setId(assets.getId());
		data.setItemsName(assets.getItems().getItemsName());
		data.setFiles(assets.getItems().getFiles());
		data.setInvoicesCode(assets.getInvoices().getInvoicesCode());
		data.setAssetsName(assets.getAssetsName());
		data.setItemsCode(assets.getItems().getItemsCode());
		data.setStatusesAssetsName(assets.getStatusesAssets().getStatusesAssetsName());
		data.setStatusesAssetsCode(assets.getStatusesAssets().getStatusesAssetsCode());
		data.setStatusesInOutName(assets.getStatusesInOut().getStatusesInOutName());
		data.setStatusesInOutCode(assets.getStatusesInOut().getStatusesInOutCode());
		data.setCompaniesCode(assets.getCompanies().getCompaniesCode());
		data.setCompaniesName(assets.getCompanies().getCompaniesName());
		data.setAssetsExpired(assets.getAssetsExpired());
		data.setCreatedBy(assets.getCreatedBy());
		data.setCreatedAt(assets.getCreatedAt());
		data.setUpdatedBy(assets.getUpdatedBy());
		data.setUpdatedAt(assets.getUpdatedAt());
		data.setIsActive(assets.getIsActive());
		data.setVersion(assets.getVersion());
		return data;
	}

	private String companiesCode()throws Exception{
		String companiesCode = employeesDao.findByUserId(getIdAuth()).getCompanies().getCompaniesCode();
		return companiesCode;
	}
	
	private String rolesCode()throws Exception{
		String roleCode = usersDao.findById(getIdAuth()).getRoles().getRolesCode();
		return roleCode;
	}
	
	@Override
	public GetAllAssetsDto findAll() throws Exception {
		String permissionCode = "PERMSN9";
		boolean validation = validation(permissionCode);
		GetAllAssetsDto assetsAll = new GetAllAssetsDto();
		if (validation) {
			if(rolesCode().equals(RolesCode.ROLES2.name())) {
				List<AssetsDataDto> listAssets = new ArrayList<AssetsDataDto>();
				List<Assets> assets = assetsDao.findAll();
				assets.forEach(asset -> {
					AssetsDataDto data = convert(asset);
					listAssets.add(data);
				});
				assetsAll.setData(listAssets);				
			}
			else {
				List<AssetsDataDto> listAssets = new ArrayList<AssetsDataDto>();
				List<Assets> assets = assetsDao.findByCompaniesCode(companiesCode());
				assets.forEach(asset -> {
					AssetsDataDto data = convert(asset);
					listAssets.add(data);
				});
				assetsAll.setData(listAssets);		
			}
			return assetsAll;
		} else {
			throw new Exception("Acces Denied");
		}
	}

	@Override
	public GetByIdAssetsDto findById(String id) throws Exception {
		String permissionCode = "PERMSN9";
		boolean validation = validation(permissionCode);
		if (validation) {
			Assets asset = assetsDao.findById(id);
			GetByIdAssetsDto getAssets = new GetByIdAssetsDto();
			AssetsDataDto data = convert(asset);
			getAssets.setData(data);
			return getAssets;
		} else {
			throw new Exception("Acces Denied");
		}
	}

	@Override
	public GetByIdAssetsDto findByAssetsName(String assetsName) throws Exception {
		String permissionCode = "PERMSN9";
		boolean validation = validation(permissionCode);
		if (validation) {
			GetByIdAssetsDto getAssets = new GetByIdAssetsDto();
			if(rolesCode().equals(RolesCode.ROLES3.name())) {
				Assets asset = assetsDao.findByAssetsNameCompany(companiesCode(),assetsName);
				AssetsDataDto data = convert(asset);
				getAssets.setData(data);
			}
			else {
				Assets asset = assetsDao.findByAssetsName(assetsName);
				AssetsDataDto data = convert(asset);
				getAssets.setData(data);
			}
			return getAssets;
		} else {
			throw new Exception("Acces Denied");
		}
	}

	@Override
	public GetAllAssetsDto findByItemsCode(String itemsCode) throws Exception {
		String permissionCode = "PERMSN9";
		boolean validation = validation(permissionCode);
		if (validation) {
			GetAllAssetsDto assetsAll = new GetAllAssetsDto();
			if(rolesCode().equals(RolesCode.ROLES3.name())) {
				List<AssetsDataDto> listAssets = new ArrayList<AssetsDataDto>();
				List<Assets> assets = assetsDao.findByItemsCodeCompany(companiesCode(),itemsCode);
				assets.forEach(asset -> {
					AssetsDataDto data = convert(asset);
					listAssets.add(data);
				});
				assetsAll.setData(listAssets);
			}
			else {
				List<AssetsDataDto> listAssets = new ArrayList<AssetsDataDto>();
				List<Assets> assets = assetsDao.findByItemsCodeCompany(companiesCode(),itemsCode);
				assets.forEach(asset -> {
					AssetsDataDto data = convert(asset);
					listAssets.add(data);
				});
				assetsAll.setData(listAssets);	
			}
			return assetsAll;
		} else {
			throw new Exception("Acces Denied");
		}
	}

	@Override
	public GetAllAssetsDto findByItemsBrandsCode(String brandsCode) throws Exception {
		String permissionCode = "PERMSN9";
		boolean validation = validation(permissionCode);
		if (validation) {
			GetAllAssetsDto assetsAll = new GetAllAssetsDto();
			if(rolesCode().equals(RolesCode.ROLES3.name())) {
				List<AssetsDataDto> listAssets = new ArrayList<AssetsDataDto>();
				List<Assets> assets = assetsDao.findByBrandsCodeCompany(companiesCode(),brandsCode);
				assets.forEach(asset -> {
					AssetsDataDto data = convert(asset);
					listAssets.add(data);
				});
				assetsAll.setData(listAssets);
			}
			else {
				List<AssetsDataDto> listAssets = new ArrayList<AssetsDataDto>();
				List<Assets> assets = assetsDao.findByBrandsCode(brandsCode);
				assets.forEach(asset -> {
					AssetsDataDto data = convert(asset);
					listAssets.add(data);
				});
				assetsAll.setData(listAssets);
			}
			return assetsAll;
		} else {
			throw new Exception("Acces Denied");
		}
	}

	@Override
	public GetAllAssetsDto findByItemsTypesCode(String itemsTypesCode) throws Exception {
		String permissionCode = "PERMSN9";
		boolean validation = validation(permissionCode);
		if (validation) {
			GetAllAssetsDto assetsAll = new GetAllAssetsDto();
			if(rolesCode().equals(RolesCode.ROLES3.name())) {
				List<AssetsDataDto> listAssets = new ArrayList<AssetsDataDto>();
				List<Assets> assets = assetsDao.findByItemsTypesCodeCompany(companiesCode(),itemsTypesCode);
				assets.forEach(asset -> {
					AssetsDataDto data = convert(asset);
					listAssets.add(data);
				});
				assetsAll.setData(listAssets);	
			}
			else {
				List<AssetsDataDto> listAssets = new ArrayList<AssetsDataDto>();
				List<Assets> assets = assetsDao.findByItemsTypesCode(itemsTypesCode);
				assets.forEach(asset -> {
					AssetsDataDto data = convert(asset);
					listAssets.add(data);
					assetsAll.setData(listAssets);
				});
			}
			return assetsAll;
		} else {
			throw new Exception("Acces Denied");
		}
	}

	@Override
	public GetAllAssetsDto findByStatusesAssetsCode(String statusesAssetsCode) throws Exception {
		String permissionCode = "PERMSN9";
		boolean validation = validation(permissionCode);
		if (validation) {
			GetAllAssetsDto assetsAll = new GetAllAssetsDto();
			if(rolesCode().equals(RolesCode.ROLES2.name())) {
				List<AssetsDataDto> listAssets = new ArrayList<AssetsDataDto>();
				List<Assets> assets = assetsDao.findByStatusesAssetsCode(statusesAssetsCode);
				assets.forEach(asset -> {
					AssetsDataDto data = convert(asset);
					listAssets.add(data);
				});
				assetsAll.setData(listAssets);
			}
			else {
				List<AssetsDataDto> listAssets = new ArrayList<AssetsDataDto>();
				List<Assets> assets = assetsDao.findByStatusesAssetsCodeCompany(companiesCode(),statusesAssetsCode);
				assets.forEach(asset -> {
					AssetsDataDto data = convert(asset);
					listAssets.add(data);
				});
				assetsAll.setData(listAssets);
			}
			
			return assetsAll;
		} else {
			throw new Exception("Access Denied");
		}
	}

	@Override
	public GetAllAssetsDto findByStatusesInOutCode(String statusesInOutCode) throws Exception {
		String permissionCode = "PERMSN9";
		boolean validation = validation(permissionCode);
		if (validation) {
			GetAllAssetsDto assetsAll = new GetAllAssetsDto();
			if(rolesCode().equals(RolesCode.ROLES3.name())) {
				List<AssetsDataDto> listAssets = new ArrayList<AssetsDataDto>();
				List<Assets> assets = assetsDao.findByStatusesInOutCodeCompany(companiesCode(),statusesInOutCode);
				assets.forEach(asset -> {
					AssetsDataDto data = convert(asset);
					listAssets.add(data);
				});
				assetsAll.setData(listAssets);				
			}
			else {
				List<AssetsDataDto> listAssets = new ArrayList<AssetsDataDto>();
				List<Assets> assets = assetsDao.findByStatusesInOutCode(statusesInOutCode);
				assets.forEach(asset -> {
					AssetsDataDto data = convert(asset);
					listAssets.add(data);
				});
				assetsAll.setData(listAssets);
			}
			return assetsAll;
		} else {
			throw new Exception("Access Denied");
		}
	}

	@Override
	public SaveAssetsResDto save(SaveAssetsReqDto saveAssetsReqDto) throws Exception {
		String permissionCode = "PERMSN10";
		boolean validation = validation(permissionCode);
		SaveAssetsResDto resDto = new SaveAssetsResDto();
		if (validation) {
			try {
				if(rolesCode().equals(RolesCode.ROLES3.name())) {
					Items item = itemsDao.findByCode(saveAssetsReqDto.getItemsCode());
					ItemsTypes itemType = itemsTypesDao.findById(item.getItemsTypes().getId());
					Invoices invoice = invoicesDao.findByCode(saveAssetsReqDto.getInvoicesCode());
					StatusesAssets statusesAssets = statusesAssetsDao.findByCode(saveAssetsReqDto.getStatusesAssetsCode());
					StatusesInOut statusesInOut = statusesInOutDao.findByCode(StatusesInOutCode.CHECKIN.getCode());
					String assetsName = generateCode(itemType.getItemsTypesName());
					Companies companies = companiesDao.findByCode(companiesCode());
					
					Assets save = new Assets();
					save.setItems(item);
					save.setInvoices(invoice);
					save.setAssetsName(assetsName);
					save.setCompanies(companies);	
					save.setStatusesAssets(statusesAssets);
					save.setStatusesInOut(statusesInOut);
					if (saveAssetsReqDto.getAssetsExpired() != null) {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
						LocalDate localDate = LocalDate.parse(saveAssetsReqDto.getAssetsExpired(), formatter);
						save.setAssetsExpired(localDate);
					}
					save.setCreatedBy(getIdAuth());
					save.setIsActive(true);
					begin();
					Assets result = assetsDao.saveOrUpdate(save);
					
					Users user = usersDao.findById(getIdAuth());
					Histories history = new Histories();
					history.setUsers(user);
					history.setAssets(result);
					history.setActivityName("CREATE");
					history.setCreatedBy(getIdAuth());
					
					historiesDao.saveOrUpdate(history);
					commit();
					
					SaveAssetsDataDto resDataDto = new SaveAssetsDataDto();
					resDataDto.setId(result.getId());
					
					
					resDto.setData(resDataDto);
					resDto.setMessage("SUCCESS");
				}
				else {
					Items item = itemsDao.findByCode(saveAssetsReqDto.getItemsCode());
					ItemsTypes itemType = itemsTypesDao.findById(item.getItemsTypes().getId());
					Invoices invoice = invoicesDao.findByCode(saveAssetsReqDto.getInvoicesCode());
					StatusesAssets statusesAssets = statusesAssetsDao.findByCode(saveAssetsReqDto.getStatusesAssetsCode());
					StatusesInOut statusesInOut = statusesInOutDao.findByCode(StatusesInOutCode.CHECKIN.getCode());
					String assetsName = generateCode(itemType.getItemsTypesName());
					Companies companies = companiesDao.findByCode(companiesCode());
					
					Assets save = new Assets();
					save.setItems(item);
					save.setInvoices(invoice);
					save.setAssetsName(assetsName);
					save.setCompanies(companies);	
					save.setStatusesAssets(statusesAssets);
					save.setStatusesInOut(statusesInOut);
					if (saveAssetsReqDto.getAssetsExpired() != null) {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
						LocalDate localDate = LocalDate.parse(saveAssetsReqDto.getAssetsExpired(), formatter);
						save.setAssetsExpired(localDate);
					}
					save.setCreatedBy(getIdAuth());
					save.setIsActive(true);
					begin();
					Assets result = assetsDao.saveOrUpdate(save);
					
					Users user = usersDao.findById(getIdAuth());
					Histories history = new Histories();
					history.setUsers(user);
					history.setAssets(result);
					history.setActivityName("CREATE");
					history.setCreatedBy(getIdAuth());
					
					historiesDao.saveOrUpdate(history);
					commit();
					
					SaveAssetsDataDto resDataDto = new SaveAssetsDataDto();
					resDataDto.setId(result.getId());
					
					
					resDto.setData(resDataDto);
					resDto.setMessage("SUCCESS");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			throw new Exception("Access Denied");
		}
		return resDto;
	}

	@Override
	public UpdateAssetsResDto update(UpdateAssetsReqDto updateAssetsReqDto) throws Exception {
		String permissionCode = "PERMSN11";
		boolean validation = validation(permissionCode);
		if (validation) {
			if(rolesCode().equals(RolesCode.ROLES3.name())) {
				Items item = itemsDao.findByCode(updateAssetsReqDto.getItemsCode());
				Invoices invoice = invoicesDao.findByCode(updateAssetsReqDto.getInvoicesCode());
				StatusesAssets statusesAssets = statusesAssetsDao.findByCode(updateAssetsReqDto.getStatusesAssetsCode());
				StatusesInOut statusesInOut = statusesInOutDao.findByCode(StatusesInOutCode.CHECKIN.getCode());
				
				Assets save = assetsDao.findByAssetsName(updateAssetsReqDto.getAssetsName());
				save.setItems(item);
				save.setInvoices(invoice);
				save.setStatusesAssets(statusesAssets);
				save.setStatusesInOut(statusesInOut);
				if (updateAssetsReqDto.getAssetsExpired() != null) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					LocalDate localDate = LocalDate.parse(updateAssetsReqDto.getAssetsExpired(), formatter);
					save.setAssetsExpired(localDate);
				}
				save.setUpdatedBy(getIdAuth());
				begin();
				Assets result = assetsDao.saveOrUpdate(save);
				commit();

				UpdateAssetsDataDto resDataDto = new UpdateAssetsDataDto();
				resDataDto.setId(result.getId());

				UpdateAssetsResDto resDto = new UpdateAssetsResDto();
				resDto.setData(resDataDto);
				resDto.setMessage("SUCCESS");
				return resDto;
			}
			else {
				Items item = itemsDao.findByCode(updateAssetsReqDto.getItemsCode());
				Invoices invoice = invoicesDao.findByCode(updateAssetsReqDto.getInvoicesCode());
				StatusesAssets statusesAssets = statusesAssetsDao.findByCode(updateAssetsReqDto.getStatusesAssetsCode());
				StatusesInOut statusesInOut = statusesInOutDao.findByCode(StatusesInOutCode.CHECKIN.getCode());
				
				Assets save = assetsDao.findByAssetsName(updateAssetsReqDto.getAssetsName());
				save.setItems(item);
				save.setInvoices(invoice);
				save.setStatusesAssets(statusesAssets);
				save.setStatusesInOut(statusesInOut);
				if (updateAssetsReqDto.getAssetsExpired() != null) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					LocalDate localDate = LocalDate.parse(updateAssetsReqDto.getAssetsExpired(), formatter);
					save.setAssetsExpired(localDate);
				}
				save.setUpdatedBy(getIdAuth());
				begin();
				Assets result = assetsDao.saveOrUpdate(save);
				commit();

				UpdateAssetsDataDto resDataDto = new UpdateAssetsDataDto();
				resDataDto.setId(result.getId());

				UpdateAssetsResDto resDto = new UpdateAssetsResDto();
				resDto.setData(resDataDto);
				resDto.setMessage("SUCCESS");
				return resDto;
			}
		} else {
			throw new Exception("Access Denied");
		}
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		String permissionCode = "PERMS12";
		boolean validation = validation(permissionCode);
		if (validation) {
			try {
				begin();
				boolean delete = assetsDao.removeById(id);
				commit();

				return delete;
			} catch (Exception e) {
				e.printStackTrace();
				rollback();
				throw new Exception(e);
			}
		} else {
			throw new Exception("Acces Denied");
		}
	}

	@Override
	public List<Assets> getTotalreq(String itemsCode,String companiesCode,
			String statusesAssetsCode, String statusesInOutCode, int total) throws Exception {
		String permissionCode = "PERMSN9";
		boolean validation = validation(permissionCode);
		if (validation) {
			if(rolesCode().equals(RolesCode.ROLES3.name())) {
				List<Assets> listAssets = assetsDao.findByReqCompany(itemsCode, companiesCode,
						statusesAssetsCode,statusesInOutCode,total);
				return listAssets;
			}
			else {
				List<Assets> listAssets = assetsDao.findByReq(itemsCode,
						statusesAssetsCode,statusesInOutCode,total);
				return listAssets;
			}
		} else {
			throw new Exception("Acces Denied");
		}
	}

	@Override
	public void saveFile(MultipartFile file) throws Exception {
		try {
			List<SaveAssetsReqDto> assets = ExcelRequest.excelToAssets(file.getInputStream());
			for (SaveAssetsReqDto asset : assets) {
				SaveAssetsReqDto save = new SaveAssetsReqDto();
				save.setItemsCode(asset.getItemsCode());
				save.setInvoicesCode(asset.getInvoicesCode());
				save.setAssetsName(asset.getAssetsName());
				save.setStatusesAssetsCode(asset.getStatusesAssetsCode());
				save.setAssetsExpired(asset.getAssetsExpired());
				save(save);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public Map<String,Object> getAssetsExpired() throws Exception {
		Map<String,Object> resMap = new HashMap<>();
		String code = companiesCode();
		List<JasperAssets> showJasper = new ArrayList<JasperAssets>();
		if(rolesCode().equals(RolesCode.ROLES3.name())) {
			List<Assets> assets = assetsDao.getExpiredAssetsCompany(code);
			for(Assets asset : assets) {
				JasperAssets jp = new JasperAssets();
				jp.setAssetsName(asset.getAssetsName());
				jp.setItemsBrandsName(asset.getItems().getItemsBrands().getItemsBrandsName());
				jp.setCompaniesName(asset.getCompanies().getCompaniesName());
				jp.setItemsName(asset.getItems().getItemsName());
				jp.setItemsTypesName(asset.getItems().getItemsTypes().getItemsTypesName());
				jp.setStatusesAssetsName(asset.getStatusesAssets().getStatusesAssetsName());
				jp.setStatusesInOutName(asset.getStatusesInOut().getStatusesInOutName());
				if(asset.getAssetsExpired()!=null) {
					DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					String assetsExpired = asset.getAssetsExpired().format(formatDate);
					jp.setAssetsExpired(assetsExpired);
				}
				else {
					jp.setAssetsExpired(null);
				}
				showJasper.add(jp);
			}
		}
		else {
			List<Assets> assets = assetsDao.getExpiredAssets();
			for(Assets asset : assets) {
				JasperAssets jp = new JasperAssets();
				jp.setAssetsName(asset.getAssetsName());
				jp.setItemsBrandsName(asset.getItems().getItemsBrands().getItemsBrandsName());
				jp.setCompaniesName(asset.getCompanies().getCompaniesName());
				jp.setItemsName(asset.getItems().getItemsName());
				jp.setItemsTypesName(asset.getItems().getItemsTypes().getItemsTypesName());
				jp.setStatusesAssetsName(asset.getStatusesAssets().getStatusesAssetsName());
				jp.setStatusesInOutName(asset.getStatusesInOut().getStatusesInOutName());
				if(asset.getAssetsExpired()!=null) {
					DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
					String assetsExpired = asset.getAssetsExpired().format(formatDate);
					jp.setAssetsExpired(assetsExpired);
				}
				else {
					jp.setAssetsExpired(null);
				}
				showJasper.add(jp);
			}
		}
		Users users = usersDao.findById(getIdAuth());
		EmailHelper emailHelper = new EmailHelper();
		emailHelper.setReceiver(users.getUsersEmail());
		emailHelper.setSubject("Report Asset Expired");
		emailHelper.setAttachmentName("Report-Asset-Expired.pdf");
		resMap.put("listJasper", showJasper);
		resMap.put("emailHelper", emailHelper);
		return resMap;
	}

	public String generateCode(String itemsType) throws Exception {
		Integer increment = assetsDao.countData() + 1;
		LocalDateTime currentDate = LocalDateTime.now();
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("ddMMyyHHmmss");
		String date = currentDate.format(formatDate);
		String code = itemsType + date + increment;
		return code;
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

	@Override
	public GetAllAssetsDto getTop5AssetsDeploy() throws Exception {
		String permissionCode = "PERMSN9";
		boolean validation = validation(permissionCode);
		if (validation) {
			GetAllAssetsDto assetsAll = new GetAllAssetsDto();
			if(rolesCode().equals(RolesCode.ROLES3.name())) {
				List<AssetsDataDto> listAssets = new ArrayList<AssetsDataDto>();
				List<Assets> assets = assetsDao.getTop5AssetsDeployCompany(companiesCode());
				assets.forEach(asset -> {
					AssetsDataDto data = convert(asset);
					listAssets.add(data);
				});
				assetsAll.setData(listAssets);
			}
			else {
				List<AssetsDataDto> listAssets = new ArrayList<AssetsDataDto>();
				List<Assets> assets = assetsDao.getTop5AssetsDeploy();
				assets.forEach(asset -> {
					AssetsDataDto data = convert(asset);
					listAssets.add(data);
				});
				assetsAll.setData(listAssets);
			}
			return assetsAll;
		} else {
			throw new Exception("Access Denied");
		}
	}

	@Override
	public GetAllAssetsDto getNewAssets() throws Exception {
		String permissionCode = "PERMSN9";
		boolean validation = validation(permissionCode);
		if (validation) {
			GetAllAssetsDto assetsAll = new GetAllAssetsDto();
			if(rolesCode().equals(RolesCode.ROLES3.name())) {
				List<AssetsDataDto> listAssets = new ArrayList<AssetsDataDto>();
				List<Assets> assets = assetsDao.getNewAssetsCompany(companiesCode());
				assets.forEach(asset -> {
					AssetsDataDto data = convert(asset);
					listAssets.add(data);
				});
				assetsAll.setData(listAssets);
			}
			else {
				List<AssetsDataDto> listAssets = new ArrayList<AssetsDataDto>();
				List<Assets> assets = assetsDao.getNewAssets();
				assets.forEach(asset -> {
					AssetsDataDto data = convert(asset);
					listAssets.add(data);
				});
				assetsAll.setData(listAssets);
			}
			return assetsAll;
		} else {
			throw new Exception("Access Denied");
		}
	}

	@Override
	public GetAllAssetsDto findByStatAssetsInOut(String statusesAssetsCode, String statusesInOutCode) throws Exception {
		String permissionCode = "PERMSN9";
		boolean validation = validation(permissionCode);
		if (validation) {
			GetAllAssetsDto assetsAll = new GetAllAssetsDto();
			if(rolesCode().equals(RolesCode.ROLES3.name())) {
				List<AssetsDataDto> listAssets = new ArrayList<AssetsDataDto>();
				List<Assets> assets = assetsDao.findByStatAssetsInOutCompany(companiesCode(),statusesAssetsCode,statusesInOutCode);
				assets.forEach(asset -> {
					AssetsDataDto data = convert(asset);
					listAssets.add(data);
				});
				assetsAll.setData(listAssets);	
			}else {
				List<AssetsDataDto> listAssets = new ArrayList<AssetsDataDto>();
				List<Assets> assets = assetsDao.findByStatAssetsInOut(statusesAssetsCode,statusesInOutCode);
				assets.forEach(asset -> {
					AssetsDataDto data = convert(asset);
					listAssets.add(data);
				});
				assetsAll.setData(listAssets);
			}
			
			return assetsAll;
		} else {
			throw new Exception("Access Denied");
		}
	}

	@Override
	public GetAllAssetsDto findByCompaniesCode(String companiesCode) throws Exception {
		String permissionCode = "PERMSN9";
		boolean validation = validation(permissionCode);
		if (validation) {
			GetAllAssetsDto assetsAll = new GetAllAssetsDto();
			List<AssetsDataDto> listAssets = new ArrayList<AssetsDataDto>();
			List<Assets> assets = assetsDao.findByCompaniesCode(companiesCode);
			assets.forEach(asset -> {
				AssetsDataDto data = convert(asset);
				listAssets.add(data);
			});
			assetsAll.setData(listAssets);
			return assetsAll;
		} else {
			throw new Exception("Acces Denied");
		}
	}
}

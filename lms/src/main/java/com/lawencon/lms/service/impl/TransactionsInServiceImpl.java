package com.lawencon.lms.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.lms.constant.EnumCode;
import com.lawencon.lms.constant.StatusesAssetsCode;
import com.lawencon.lms.constant.StatusesInOutCode;
import com.lawencon.lms.dao.AssetsDao;
import com.lawencon.lms.dao.EmployeesDao;
import com.lawencon.lms.dao.HistoriesDao;
import com.lawencon.lms.dao.LocationsDao;
import com.lawencon.lms.dao.StatusesAssetsDao;
import com.lawencon.lms.dao.StatusesInOutDao;
import com.lawencon.lms.dao.StatusesTransactionsDao;
import com.lawencon.lms.dao.TransactionsDetailInDao;
import com.lawencon.lms.dao.TransactionsInDao;
import com.lawencon.lms.dao.UsersDao;
import com.lawencon.lms.dto.transactionsin.GetAllTransactionsInResDto;
import com.lawencon.lms.dto.transactionsin.GetByTransactionsInCodeResDto;
import com.lawencon.lms.dto.transactionsin.GetByTransactionsInIdResDto;
import com.lawencon.lms.dto.transactionsin.GetTransactionsInDataDto;
import com.lawencon.lms.dto.transactionsin.SaveFullTransactionsInReqDto;
import com.lawencon.lms.dto.transactionsin.SaveFullTransactionsInResDto;
import com.lawencon.lms.dto.transactionsin.SaveTransactionsDetailsInReqDto;
import com.lawencon.lms.dto.transactionsin.SaveTransactionsDetailsInResDto;
import com.lawencon.lms.dto.transactionsin.SaveTransactionsInResDto;
import com.lawencon.lms.model.Assets;
import com.lawencon.lms.model.Employees;
import com.lawencon.lms.model.Histories;
import com.lawencon.lms.model.Locations;
import com.lawencon.lms.model.StatusesAssets;
import com.lawencon.lms.model.StatusesInOut;
import com.lawencon.lms.model.StatusesTransactions;
import com.lawencon.lms.model.TransactionsDetailIn;
import com.lawencon.lms.model.TransactionsIn;
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
	
	@Override
	public GetAllTransactionsInResDto findAll() throws Exception {
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
		results.setMessage("Sukses");
		return results;
	}

	@Override
	public GetByTransactionsInIdResDto findById(String id) throws Exception {
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

	@Override
	public GetByTransactionsInCodeResDto findByCode(String code) throws Exception {
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

	@Override
	public SaveFullTransactionsInResDto save(SaveFullTransactionsInReqDto saveFullReq) throws Exception {
		SaveFullTransactionsInResDto saveFullResDto = new SaveFullTransactionsInResDto();
		SaveTransactionsInResDto saveResDto = new SaveTransactionsInResDto();
		List<SaveTransactionsDetailsInResDto> detailsRes = new ArrayList<>();
		TransactionsIn transactionsIn = new TransactionsIn();

		try {
			begin();
			transactionsIn.setTransactionsInCode(generateCode());
			transactionsIn.setTransactionsInDate(LocalDateTime.now());

			TransactionsIn tin = transactionsInDao.saveOrUpdate(transactionsIn);
			
			for (SaveTransactionsDetailsInReqDto saveDet : saveFullReq.getTransactionsDetailDto()) {
				SaveTransactionsDetailsInResDto detail = new SaveTransactionsDetailsInResDto();
				TransactionsDetailIn tdin = new TransactionsDetailIn();
				
				Locations location = locationsDao.findByCode(saveDet.getLocationsId());
				Employees employees = employeesDao.findById(saveDet.getEmployeesId());
				Assets assets = assetsDao.findByAssetsName(saveDet.getAssetsId());
				StatusesTransactions statusesTransactions = statusesTransactionsDao.findById(saveDet.getAssetsId());
				
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
				try {
					users = usersDao.findById(getIdAuth());
				} catch (Exception e) {
					e.printStackTrace();
				}
				histories.setAssets(tdin.getAssets());
				histories.setUsers(users);
				histories.setActivityName(StatusesInOutCode.CHECKIN.getCode());
				try {
					histories = historiesDao.saveOrUpdate(histories);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			saveFullResDto.setSaveTransactionsInResDto(saveResDto);
			saveFullResDto.setMessage("Sukses");
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		

		return saveFullResDto;
	}
	
	public String generateCode() throws Exception {
		String generatedCode = transactionsInDao.countData() + EnumCode.INVOICES.getCode();
		return generatedCode;
	}

}

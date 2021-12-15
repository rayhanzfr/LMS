package com.lawencon.lms.dao.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.TransactionsDetailInDao;
import com.lawencon.lms.model.Assets;
import com.lawencon.lms.model.Employees;
import com.lawencon.lms.model.Locations;
import com.lawencon.lms.model.StatusesTransactions;
import com.lawencon.lms.model.TransactionsDetailIn;
import com.lawencon.lms.model.TransactionsIn;

@Repository()
public class TransactionsDetailInDaoImpl extends BaseDaoImpl<TransactionsDetailIn> implements TransactionsDetailInDao {

	@Override
	public List<TransactionsDetailIn> findAll() throws Exception {
		return getAll();
	}

	@Override
	public TransactionsDetailIn findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public List<TransactionsDetailIn> findByTransactionInCode(String code) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(
				" SELECT tdin.id, tdin.transactions_in_code, tdin.locations_deploy, tdin.employees_fullname, tdin.assets_name, tdin.statuses_transactions_name, tdin.return_date, tdin.version, tdin.created_at, tdin.created_by ");
		sql.append(" FROM transactions_detail_in as tdin");
		sql.append(" INNER JOIN transactions_in as tin ON tin.id = tdin.transactions_in_id ");
		sql.append(" INNER JOIN locations as l ON l.id = tin.locations_id ");
		sql.append(" INNER JOIN employees as e ON e.id = tin.employees_id ");
		sql.append(" INNER JOIN assets as e ON a.id = tin.assets_id ");
		sql.append(" INNER JOIN statuses_transactions as st ON st.id = tin.statuses_transactions_id ");
		sql.append(" WHERE companies_code = :companies_code ");


		List<?> resultQuery = createNativeQuery(sql.toString()).setParameter("companies_code", code).getResultList();

		List<TransactionsDetailIn> resultDetail = new ArrayList<>();
		resultQuery.forEach(rq -> {
			TransactionsDetailIn transactionsDetailIn = new TransactionsDetailIn();
			Object[] objArr = (Object[]) rq;
			
			String id = objArr[0].toString();
			String transactionsInCode = objArr[1].toString();
			String locationsDeploy = objArr[2].toString();
			String employeesFullname = objArr[3].toString();
			String assetsName = objArr[4].toString();
			String statusesTransactionsName = objArr[5].toString();
			LocalDateTime returnDate = Timestamp.valueOf(objArr[6].toString()).toLocalDateTime();
			Integer version = (Integer) objArr[7];
			LocalDateTime createdAt = Timestamp.valueOf(objArr[8].toString()).toLocalDateTime();
			String createdBy = objArr[9].toString();

			TransactionsIn tin = new TransactionsIn();
			tin.setTransactionsInCode(transactionsInCode);

			Locations locations = new Locations();
			locations.setLocationsDeploy(locationsDeploy);

			Employees employees = new Employees();
			employees.setEmployeesFullname(employeesFullname);

			Assets assets = new Assets();
			assets.setAssetsName(assetsName);

			StatusesTransactions statTrans = new StatusesTransactions();
			statTrans.setStatusesTransactionsName(statusesTransactionsName);

			transactionsDetailIn.setId(id);
			transactionsDetailIn.setTransactionsIn(tin);
			transactionsDetailIn.setLocations(locations);
			transactionsDetailIn.setEmployees(employees);
			transactionsDetailIn.setAssets(assets);
			transactionsDetailIn.setStatusesTransactions(statTrans);
			transactionsDetailIn.setReturnDate(returnDate);
			transactionsDetailIn.setVersion(version);
			transactionsDetailIn.setCreatedAt(createdAt);
			transactionsDetailIn.setCreatedBy(createdBy);
			
			resultDetail.add(transactionsDetailIn);
		});

		return resultDetail;
	}

	@Override
	public TransactionsDetailIn saveOrUpdate(TransactionsDetailIn transactionsIn) throws Exception {
		return save(transactionsIn);
	}

}

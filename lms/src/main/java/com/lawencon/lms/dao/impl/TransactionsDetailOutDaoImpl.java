package com.lawencon.lms.dao.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.parsing.Location;
import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.TransactionsDetailOutDao;
import com.lawencon.lms.model.Assets;
import com.lawencon.lms.model.Employees;
import com.lawencon.lms.model.Locations;
import com.lawencon.lms.model.TransactionsDetailOut;
import com.lawencon.lms.model.TransactionsOut;

@Repository
public class TransactionsDetailOutDaoImpl extends BaseDaoImpl<TransactionsDetailOut>
		implements TransactionsDetailOutDao {

	@Override
	public TransactionsDetailOut findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public List<TransactionsDetailOut> findByTransactionOutCode(String tansactionOutCode) throws Exception {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT tdo.id, to.transactions_out_code, l.locations_deploy, e.employees_fullname, a.assets_name, tdo.transactions_detail_out_expired, tdo.created_by, tdo.created_at, tdo.updated_by, tdo.updated_at, tdo.version ");
			sql.append(" FROM transactions_detail_out tdo ");
			sql.append(" INNER JOIN transactions_out as to ON to.id = tdo.transactions_out_id ");
			sql.append(" INNER JOIN locations as l ON l.id = tdo.locations_id ");
			sql.append(" INNER JOIN employees as e ON e.id = tdo.employees_id ");
			sql.append(" INNER JOIN assets as a ON a.id = tdo.assets_id ");
			sql.append(" WHERE to.transactions_out_code = :tansactionOutCode");
			
			List<?> resultQuery = createNativeQuery(sql.toString())
					.setParameter("tansactionOutCode", tansactionOutCode)
					.getResultList();
			List<TransactionsDetailOut> listDetail = new ArrayList<TransactionsDetailOut>();
			if(resultQuery!=null) {
				resultQuery.forEach(rs->{
					Object[] obj = (Object[]) rs;
					TransactionsDetailOut detail = new TransactionsDetailOut();
					detail.setId(obj[0].toString());
					
					TransactionsOut outHeader = new TransactionsOut();
					outHeader.setTransactionsOutCode(obj[1].toString());
					detail.setTransactionsOut(outHeader);
					
					Locations locations = new Locations();
					locations.setLocationsDeploy(obj[2].toString());
					detail.setLocations(locations);
					
					Employees employees = new Employees();
					employees.setEmployeesFullname(obj[3].toString());
					detail.setEmployees(employees);
					
					Assets assets = new Assets();
					assets.setAssetsName(obj[4].toString());
					detail.setAssets(assets);
					
					if(obj[5]!=null) {
						detail.setTransactionDetailOutExpired(LocalDate.parse(obj[5].toString()));
					}
					
					detail.setCreatedBy(obj[6].toString());
					detail.setCreatedAt(Timestamp.valueOf(obj[7].toString()).toLocalDateTime());
					
					if(obj[8]!=null) {
						detail.setUpdatedBy(obj[8].toString());
					}
					
					if(obj[9]!=null) {
						detail.setUpdatedAt(Timestamp.valueOf(obj[9].toString()).toLocalDateTime());
					}
					
					detail.setVersion((Integer)obj[10]);
					listDetail.add(detail);
				});
			}
		return listDetail;
	}

	@Override
	public TransactionsDetailOut saveOrUpdate(TransactionsDetailOut transactionsDetailOut) throws Exception {
		return save(transactionsDetailOut);
	}

	@Override
	public List<TransactionsDetailOut> findMoreThanExpiredDate() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT tdo.id tro.transactions_out_code,a.assets_name, l.locations_deploy, e.employees_fullname, tdo.transaction_detail_out_expired  ");
		sql.append(" FROM transactions_detail_out tdo ");
		sql.append(" INNER JOIN transactions_out tro ON tro.id = tdo.transactions_out_id ");
		sql.append(" INNER JOIN locations l ON l.id = tdo.locations_id  ");
		sql.append(" INNER JOIN employees e ON e.id =tdo.employees_id  ");
		sql.append(" INNER JOIN assets a ON a.id = tdo.assets_id ");
		sql.append(" WHERE tdo.transaction_detail_out_expired IS NOT NULL AND tdo.transaction_detail_out_expired <= DATE(NOW()) ");
		
		List<?>result = createNativeQuery(sql.toString())
				.getResultList();
		List<TransactionsDetailOut>listTO = new ArrayList<TransactionsDetailOut>();
		result.forEach(rs->{
			Object[] obj = (Object[])rs;
			
			TransactionsDetailOut TDO = new TransactionsDetailOut();
			TDO.setId(obj[0].toString());
			
			TransactionsOut transOut = new TransactionsOut();
			transOut.setTransactionsOutCode(obj[1].toString());
			
			Assets assets = new Assets();
			assets.setAssetsName(obj[2].toString());
			
			Locations location = new Locations();
			location.setLocationsDeploy(obj[3].toString());
			
			Employees employe = new Employees();
			employe.setEmployeesFullname(obj[4].toString());
			
			TDO.setTransactionsOut(transOut);
			TDO.setAssets(assets);
			TDO.setLocations(location);
			TDO.setEmployees(employe);
			if(obj[5]!=null) {
				TDO.setTransactionDetailOutExpired(Timestamp.valueOf(obj[5].toString()).toLocalDateTime().toLocalDate());
			}
			
			listTO.add(TDO);
		});
		
		return listTO;
	}

}

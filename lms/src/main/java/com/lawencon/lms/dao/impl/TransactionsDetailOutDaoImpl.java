package com.lawencon.lms.dao.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.lms.dao.TransactionsDetailOutDao;
import com.lawencon.lms.model.Assets;
import com.lawencon.lms.model.Companies;
import com.lawencon.lms.model.Employees;
import com.lawencon.lms.model.Items;
import com.lawencon.lms.model.Locations;
import com.lawencon.lms.model.TransactionsDetailOut;
import com.lawencon.lms.model.TransactionsOut;
import com.lawencon.lms.model.Users;

@Repository
public class TransactionsDetailOutDaoImpl extends BaseDaoImpl<TransactionsDetailOut>
		implements TransactionsDetailOutDao {

	@Override
	public TransactionsDetailOut findById(String id) throws Exception {
		return getById(id);
	}

	@Override
	public List<TransactionsDetailOut> findByTransactionOutCode(String transactionOutCode) throws Exception {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT tdo ");
			sql.append(" FROM TransactionsDetailOut tdo ");
			sql.append(" INNER JOIN FETCH tdo.transactionsOut ");
			sql.append(" LEFT JOIN FETCH tdo.locations l ");
			sql.append(" LEFT JOIN FETCH tdo.employees e ");
			sql.append(" INNER JOIN FETCH tdo.assets a ");
			sql.append(" WHERE tdo.transactionsOut.transactionsOutCode = :transactionOutCode");
			sql.append(" AND (l IS NOT NULL OR l IS NULL) ");
			sql.append(" AND (e IS NOT NULL OR e IS NULL) ");
			
			List<TransactionsDetailOut> listDetail = createQuery(sql.toString(), TransactionsDetailOut.class)
					.setParameter("transactionOutCode", transactionOutCode)
					.getResultList();
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

	@Override
	public List<TransactionsDetailOut> findAlmostExpired() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT tdo.id , transout.transactions_out_code, a.assets_name , e.employees_fullname, u.users_email , l.locations_deploy , c.companies_name, tdo.transaction_detail_out_expired, i.items_name  ");
		sql.append(" FROM transactions_detail_out tdo  ");
		sql.append(" INNER JOIN assets a ON a.id = tdo.assets_id ");
		sql.append(" INNER JOIN transactions_out transout ON transout.id = tdo.transactions_out_id ");
		sql.append(" INNER JOIN locations l ON l.id = tdo.locations_id OR tdo.locations_id IS NULL ");
		sql.append(" INNER JOIN companies c ON c.id = l.companies_id OR l.companies_id IS NULL ");
		sql.append(" INNER JOIN employees e ON e.id = tdo.employees_id OR tdo.employees_id IS NULL ");
		sql.append(" INNER JOIN users u ON u.id = e.users_id OR e.users_id IS NULL ");
		sql.append(" INNER JOIN items i ON i.id = a.items_id OR a.items_id IS NULL ");
		sql.append(" WHERE (EXTRACT(DAY FROM tdo.transaction_detail_out_expired)- EXTRACT(DAY FROM now())) <=7 ");
		
		List<?> result = createNativeQuery(sql.toString())
				.getResultList();
		List<TransactionsDetailOut> data = new ArrayList<TransactionsDetailOut>();
		result.forEach(rs->{
			Object[] obj = (Object[]) rs;
			TransactionsDetailOut detail = new TransactionsDetailOut();
			detail.setId(obj[0].toString());
			
			TransactionsOut out = new TransactionsOut();
			out.setTransactionsOutCode(obj[1].toString());
			
			Assets asset = new Assets();
			asset.setAssetsName(obj[2].toString());
			
			Employees employee = new  Employees();
			if(obj[3]!=null) {
				employee.setEmployeesFullname(obj[3].toString());
			}
			
			Users user = new Users();
			if(obj[4]!=null) {
				user.setUsersEmail(obj[4].toString());
			}
			employee.setUsers(user);
			
			Locations location =  new Locations();
			if(obj[5]!=null) {
				location.setLocationsDeploy(obj[5].toString());
			}
			
			Companies company = new Companies();
			if(obj[6]!=null) {
				company.setCompaniesName(obj[6].toString());
			}
			location.setCompanies(company);
			
			detail.setAssets(asset);
			detail.setEmployees(employee);
			detail.setLocations(location);
			detail.setTransactionsOut(out);
			detail.setTransactionDetailOutExpired(LocalDate.parse(obj[7].toString()));
			
			Items items = new Items();
			items.setItemsName(obj[8].toString());
			asset.setItems(items);
			
			data.add(detail);
		});
		return data;
	}

}

package com.lawencon.lms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.lms.dao.AssetsDao;
import com.lawencon.lms.dao.EmployeesDao;
import com.lawencon.lms.dao.HistoriesDao;
import com.lawencon.lms.dao.UsersDao;
import com.lawencon.lms.dto.histories.HistoriesReportResDto;
import com.lawencon.lms.model.Assets;
import com.lawencon.lms.model.Employees;
import com.lawencon.lms.model.Histories;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.HistoriesService;

@Service
public class HistoriesServiceImpl extends BaseServiceLmsImpl implements HistoriesService {

	@Autowired
	private HistoriesDao historiesDao;

	@Autowired
	private AssetsDao assetsDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private EmployeesDao employeesDao;

	@Override
	public Histories save(Histories histories) throws Exception {
		try {
			begin();
			histories = historiesDao.saveOrUpdate(histories);
			commit();
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return histories;
	}

	@Override
	public Histories findById(String id) throws Exception {
		return historiesDao.findById(id);
	}

	@Override
	public List<Histories> findAll() throws Exception {
		return historiesDao.findAll();
	}

	@Override
	public List<Histories> findByUsersId(String usersId) throws Exception {
		return historiesDao.findByUsersId(usersId);
	}

	public String companiesCode() throws Exception {
		Employees employees = employeesDao.findByUserId(getIdAuth());
		String companiesCode = employees.getCompanies().getCompaniesCode();
		return companiesCode;
	}

	@Override
	public Map<String, Object> findHistoriesReport() throws Exception {
		Map<String, Object> resMap = new HashMap<>();
		List<Histories> listHistories = historiesDao.findAll();
		List<HistoriesReportResDto> listHistoriesReportResDto = new ArrayList<HistoriesReportResDto>();

		listHistories.forEach(i -> {
			try {
				Users usersNow = usersDao.findById(i.getUsers().getId());
				Employees employeesNow = employeesDao.findByUserId(usersNow.getId());

				HistoriesReportResDto historiesReportResDto = new HistoriesReportResDto();
				Assets assets = new Assets();
				try {
					assets = assetsDao.findById(i.getAssets().getId());
				} catch (Exception e) {
					e.printStackTrace();
				}
				historiesReportResDto.setAssetsName(assets.getAssetsName());
				Users users = new Users();
				try {
					users = usersDao.findById(i.getUsers().getId());
					historiesReportResDto.setUsersEmail(users.getUsersEmail());
				} catch (Exception e) {
					e.printStackTrace();
				}
				Employees employees = new Employees();
				try {
					employees = employeesDao.findByUserId(users.getId());
					historiesReportResDto.setEmployeesName(employees.getEmployeesFullname());

				} catch (Exception e) {
					e.printStackTrace();
				}
				historiesReportResDto.setActivityName(i.getActivityName());
				listHistoriesReportResDto.add(historiesReportResDto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		resMap.put("listJasper", listHistoriesReportResDto);
		return resMap;
	}

	@Override
	public Map<String, Object> findHistoriesReportNonAdmin(String companiesCode) throws Exception {
		Map<String, Object> resMap = new HashMap<>();
		List<Histories> listHistories = historiesDao.findAll();
		List<HistoriesReportResDto> listHistoriesReportResDto = new ArrayList<HistoriesReportResDto>();

		listHistories.forEach(i -> {
			try {
				Users usersNow = usersDao.findById(i.getUsers().getId());
				Employees employeesNow = employeesDao.findByUserId(usersNow.getId());
				if (companiesCode.equals(employeesNow.getCompanies().getCompaniesCode())) {
					HistoriesReportResDto historiesReportResDto = new HistoriesReportResDto();
					Assets assets = new Assets();
					try {
						assets = assetsDao.findById(i.getAssets().getId());
					} catch (Exception e) {
						e.printStackTrace();
					}
					historiesReportResDto.setAssetsName(assets.getAssetsName());
					Users users = new Users();
					try {
						users = usersDao.findById(i.getUsers().getId());
						historiesReportResDto.setUsersEmail(users.getUsersEmail());
					} catch (Exception e) {
						e.printStackTrace();
					}
					Employees employees = new Employees();
					try {
						employees = employeesDao.findByUserId(users.getId());
						historiesReportResDto.setEmployeesName(employees.getEmployeesFullname());

					} catch (Exception e) {
						e.printStackTrace();
					}
					historiesReportResDto.setActivityName(i.getActivityName());
					listHistoriesReportResDto.add(historiesReportResDto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		resMap.put("listJasper", listHistoriesReportResDto);
		return resMap;
	}

}

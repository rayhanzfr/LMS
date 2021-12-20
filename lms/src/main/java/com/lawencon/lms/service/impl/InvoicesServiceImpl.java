package com.lawencon.lms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.lms.constant.EnumCode;
import com.lawencon.lms.dao.InvoicesDao;
import com.lawencon.lms.dto.invoices.SaveInvoicesResDto;
import com.lawencon.lms.dto.invoices.UpdateInvoicesResDto;
import com.lawencon.lms.model.Invoices;
import com.lawencon.lms.model.Users;
import com.lawencon.lms.service.InvoicesService;
import com.lawencon.lms.service.UsersService;

@Service
public class InvoicesServiceImpl extends BaseServiceLmsImpl implements InvoicesService {

	@Autowired
	private UsersService usersService;

	@Autowired
	private InvoicesDao invoicesDao;

	@Override
	public List<Invoices> findAll() throws Exception {
		return invoicesDao.findAll();
	}

	@Override
	public Invoices findById(String id) throws Exception {
		return invoicesDao.findById(id);
	}

	@Override
	public Invoices findByCode(String code) throws Exception {
		return invoicesDao.findByCode(code);
	}

	@Override
	public SaveInvoicesResDto save(Invoices invoices) throws Exception {
		SaveInvoicesResDto saveRes = new SaveInvoicesResDto();

		try {

			Users users = usersService.findById(invoices.getCreatedBy());
			if (!users.getRoles().getRolesName().equals("SUPER-ADMIN") && users.getIsActive() == false) {
				throw new IllegalAccessException("only superAdmin can Insert data!");
			}

			begin();
			invoices.setInvoicesCode(generateCode());
			invoices = invoicesDao.saveOrUpdate(invoices);
			commit();

			saveRes.setId(invoices.getId());
			saveRes.setMessage("Inserted");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return saveRes;
	}

	@Override
	public UpdateInvoicesResDto update(Invoices invoices) throws Exception {
		UpdateInvoicesResDto updateRes = new UpdateInvoicesResDto();

		try {
			Invoices invoicesDb = findById(invoices.getId());
			invoices.setCreatedAt(invoicesDb.getCreatedAt());
			invoices.setCreatedBy(invoicesDb.getCreatedBy());

			Users users = usersService.findById(invoices.getUpdatedBy());
			if (!users.getRoles().getRolesName().equals("SUPER-ADMIN") && users.getIsActive() == false) {
				throw new IllegalAccessException("only superAdmin can Update data!");
			}

			begin();
			invoices = invoicesDao.saveOrUpdate(invoices);
			commit();

			updateRes.setVersion(invoices.getVersion());
			updateRes.setMessage("Inserted");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return updateRes;
	}

	@Override
	public Boolean removeById(String id) throws Exception {
		return invoicesDao.removeById(id);
	}

	public String generateCode() throws Exception {
		String generatedCode = invoicesDao.countData() + EnumCode.INVOICES.getCode();
		return generatedCode;
	}

}

package com.lawencon.lms.service.impl;

import java.time.LocalDateTime;
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

			Users users = usersService.findById(getIdAuth());
			if (users == null) {
				throw new IllegalAccessException("You need to login first");
			}

			else if (!users.getRoles().getRolesName().equals("SUPER-ADMIN") || users.getIsActive() == false) {
				saveRes.setMessage("only superAdmin can Insert data!");
				throw new IllegalAccessException("only superAdmin can Insert data!");
			}

			else {
				if (invoices.getStoreName() == null || invoices.getStoreName().length() > 50) {
					throw new Exception("storeName required and not longer than 50 character length");
				}

				else if (invoices.getPrice() == null) {
					throw new Exception("price required");
				}

				else {
					begin();
					invoices.setInvoicesDate(LocalDateTime.now());
					invoices.setInvoicesCode(generateCode());
					invoices.setCreatedBy(getIdAuth());
					invoices = invoicesDao.saveOrUpdate(invoices);
					commit();

					saveRes.setId(invoices.getId());
					saveRes.setMessage("Inserted");
				}

			}

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
			Invoices invoicesDb = findByCode(invoices.getInvoicesCode());
			invoicesDb.setUpdatedBy(getIdAuth());
			invoicesDb.setStoreName(invoices.getStoreName());
			invoicesDb.setPrice(invoices.getPrice());
			
			Users users = usersService.findById(getIdAuth());

			if (users == null) {
				throw new IllegalAccessException("You need to login first!");
			}

			else if (!users.getRoles().getRolesName().equals("SUPER-ADMIN") || users.getIsActive() == false) {
				throw new IllegalAccessException("only superAdmin can Update data!");
			}

			else {
				begin();
				invoices = invoicesDao.saveOrUpdate(invoicesDb);
				commit();
				updateRes.setVersion(invoices.getVersion());
				updateRes.setMessage("Inserted");
			}

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
		String generatedCode = EnumCode.INVOICES.getCode() + (invoicesDao.countData() + 1);
		return generatedCode;
	}

}

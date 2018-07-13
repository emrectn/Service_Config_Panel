package com.example.demo.service;

import java.util.List;

import com.example.demo.model.CostType;
import com.example.demo.model.PermType;
import com.example.demo.model.StatusType;

public interface AppService {
	
	List<CostType> editCostTypeList(List<CostType> ctypes);

	List<PermType> editPermTypeList(List<PermType> ptypes);

	List<StatusType> editStatusTypeList(List<StatusType> stypes);

}

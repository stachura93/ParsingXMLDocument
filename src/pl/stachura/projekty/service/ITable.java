package pl.stachura.projekty.service;

import java.util.ArrayList;
import java.util.HashMap;


public interface ITable {
	 String createSimpleTableUseModelClass(HashMap<String, ArrayList<String>> tHeadAndTBody );
	 String getAllScriptAndStylesheet();
}

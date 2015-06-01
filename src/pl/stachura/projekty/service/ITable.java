package pl.stachura.projekty.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public interface ITable {
	 String createSimpleTableUseModelClass(HashMap<String, ArrayList<String>> tHeadAndTBody ) throws IOException;
	 String getAllScriptAndStylesheet();
}

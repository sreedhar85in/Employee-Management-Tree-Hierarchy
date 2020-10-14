package com.kiwiplan.treeproject;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ManagementHierarchy {

	public static Map<Long, Employees> employeeMap;
	public static Employees rootofthetree;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		employeeMap = new HashMap<Long, Employees>();
		List<Employees> employeeList = readInput();
		consoleDisplay(employeeList);
	}

	private static List<Employees> readInput() {

		final String dir = System.getProperty("user.dir");
		String path = dir.replace("/", "\\\\") + "\\src\\main\\resources\\";

		List<Employees> employeesList = new ArrayList<>();

		JSONParser parser = new JSONParser();

		try (Reader reader = new FileReader(path + "input2.json")) {
			JSONObject jsonObject = (JSONObject) parser.parse(reader);
			// loop array
			JSONArray array = (JSONArray) jsonObject.get("employees");
			Iterator<JSONObject> iterator = array.iterator();
			Employees employee = null;

			while (iterator.hasNext()) {
				JSONObject object = iterator.next();
				String name = (String) object.get("name");
				long employeeId = (Long) object.get("id");
				long managerId = (Long) object.get("mId");
				employee = new Employees(managerId, employeeId, name);
				employeesList.add(employee);
			}

		} catch (IOException e) {
			System.err.println("IOException: " + e);
		} catch (ParseException e) {
			System.err.println("ParseException: " + e);
		}

		return employeesList;
	}

	private static void createLevels(Employees localRoot) {
		Employees employee = localRoot;
		List<Employees> reportees = getreporteesById(employee.getEmployeeId());
		Collections.sort(reportees, new NameSort());
		employee.setReportees(reportees);
		if (reportees.size() == 0) {
			return;
		}

		for (Employees e : reportees) {
			createLevels(e);
		}
	}

	private static List<Employees> getreporteesById(long rid) {
		List<Employees> reportees = new ArrayList<Employees>();
		for (Employees e : employeeMap.values()) {
			if (e.getManagerId() == rid) {
				reportees.add(e);
			}
		}
		return reportees;
	}

	private static void print(Employees localRoot, int level) {

		for (int i = 0; i < level; i++) {
			System.out.print("->");
		}

		List<Employees> reportees = localRoot.getReportees();
		System.out.print("->");
		System.out.println(localRoot.getName());

		for (Employees e : reportees) {
			print(e, level + 1);
		}
	}

	public static void consoleDisplay(List<Employees> employeeList) {
		for (Employees employee : employeeList) {
			employeeMap.put(employee.getEmployeeId(), employee);
			if (employee.getManagerId() == 0) {
				rootofthetree = employee;
			}
		}
		createLevels(rootofthetree);
		print(rootofthetree, 0);
	}

}

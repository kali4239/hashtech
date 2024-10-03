import java.util.*;

public class Emp{
    private Map<String, List<Map<String, String>>> collections = new HashMap<>();
    public void createCollection(String collectionName) {
        collections.put(collectionName, new ArrayList<>());
        System.out.println("Collection created: " + collectionName);
    }
    public void indexData(String collectionName, String excludeColumn) {
        List<Map<String, String>> collection = collections.get(collectionName);
        if (collection == null) {
            System.out.println("Collection not found: " + collectionName);
            return;
        }
        List<Map<String, String>> employeeData = Arrays.asList(
            new HashMap<>(Map.of("EmployeeId", "E02001", "Name", "nagesh", "Department", "IT", "Gender", "Female")),
            new HashMap<>(Map.of("EmployeeId", "E02002", "Name", "kali", "Department", "HR", "Gender", "Male")),
            new HashMap<>(Map.of("EmployeeId", "E02003", "Name", "arun", "Department", "IT", "Gender", "Male"))
        );
        for (Map<String, String> employee : employeeData) {
            employee.remove(excludeColumn); 
            collection.add(employee);
        }
        System.out.println("Indexed data into collection: " + collectionName);
    }
    public List<Map<String, String>> searchByColumn(String collectionName, String columnName, String columnValue) {
        List<Map<String, String>> collection = collections.get(collectionName);
        List<Map<String, String>> results = new ArrayList<>();
        if (collection != null) {
            for (Map<String, String> record : collection) {
                if (columnValue.equals(record.get(columnName))) {
                    results.add(record);
                }
            }
        }
        return results;
    }
    public int getEmpCount(String collectionName) {
        List<Map<String, String>> collection = collections.get(collectionName);
        return collection != null ? collection.size() : 0;
    }
    public void delEmpById(String collectionName, String employeeId) {
        List<Map<String, String>> collection = collections.get(collectionName);
        if (collection != null) {
            collection.removeIf(record -> employeeId.equals(record.get("EmployeeId")));
            System.out.println("Deleted employee with ID: " + employeeId + " from collection: " + collectionName);
        }
    }
    public Map<String, Integer> getDepFacet(String collectionName) {
        List<Map<String, String>> collection = collections.get(collectionName);
        Map<String, Integer> departmentCount = new HashMap<>();
        if (collection != null) {
            for (Map<String, String> record : collection) {
                String dept = record.get("Department");
                departmentCount.put(dept, departmentCount.getOrDefault(dept, 0) + 1);
            }
        }
        return departmentCount;
    }
    public static void main(String[] args) {
        Emp ec = new Emp();
        String v_nameCollection = "kalimuthu";
        String v_phoneCollection = "0353"; 
        ec.createCollection(v_nameCollection);
        ec.createCollection(v_phoneCollection);
        System.out.println("Employee Count in " + v_nameCollection + ": " + ec.getEmpCount(v_nameCollection));
        ec.indexData(v_nameCollection, "Department");
        ec.indexData(v_phoneCollection, "Gender");
        ec.delEmpById(v_nameCollection, "E02003");
        System.out.println("Employee Count in " + v_nameCollection + ": " + ec.getEmpCount(v_nameCollection));
        System.out.println("Search IT in " + v_nameCollection + ": " + ec.searchByColumn(v_nameCollection, "Department", "IT"));
        System.out.println("Search Male in " + v_nameCollection + ": " + ec.searchByColumn(v_nameCollection, "Gender", "Male"));
        System.out.println("Search IT in " + v_phoneCollection + ": " + ec.searchByColumn(v_phoneCollection, "Department", "IT"));        
        System.out.println("Department Facet in " + v_nameCollection + ": " + ec.getDepFacet(v_nameCollection));
        System.out.println("Department Facet in " + v_phoneCollection + ": " + ec.getDepFacet(v_phoneCollection));
    }
}

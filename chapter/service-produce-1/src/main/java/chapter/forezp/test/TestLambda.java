package chapter.forezp.test;

import java.util.*;

public class TestLambda {

	static List<Employ> employs = Arrays.asList(
		new Employ(18, 5555.55, "张三"),
		new Employ(22, 6666.66, "李四"),
		new Employ(33, 3333.33, "王五"),
		new Employ(44, 9999.99, "赵六"),
		new Employ(55, 8888.88, "田七"));
	
	//定义过滤员工返回新员工集合方法
	public static List<Employ>  getEmployByFilter(List<Employ> employs, EmployStrategy employStrategy){
		List<Employ> newEmploys = new ArrayList<Employ>();
		for(Employ employ : employs) {
			if(employStrategy.filterEmploy(employ)) {
				newEmploys.add(employ);
			}
		}
		return newEmploys;
	}
	
	//通过策略模式实现
	public static void test() {
		//根据年龄过滤
		List<Employ> ageFilterEmploys = getEmployByFilter(employs, new EmployFilterByAge());
		System.out.println("年龄大于25的员工：");
		for (Employ e : ageFilterEmploys) {
			System.out.println(e);
		}
		
		//根据工资过滤
		List<Employ> salaryFilterEmploys = getEmployByFilter(employs, new EmployFilterBySalary());
		System.out.println("工资大于6000的员工：");
		for (Employ e : salaryFilterEmploys) {
			System.out.println(e);
		}

		List<Employ> employByFilterByName = getEmployByFilter(employs, new EmployFilterByName());
		System.out.println("员工名字田七：");
		for (Employ e : employByFilterByName){
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
//		test();
//			Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
//			int r = com.compare(10, 8);
//			System.out.println(r);
		Collections.sort(employs, new Comparator<Employ>() {
			@Override
			public int compare(Employ o1, Employ o2) {
				return o1.getAge().compareTo(o2.getAge());
			}
		});
		System.out.println(employs);

		Collections.sort(employs, (Employ o1,Employ o2) ->{
				return o2.getAge().compareTo(o1.getAge());
		});
		System.out.println(employs);

//		System.out.println(("3127893129") ->{Integer::valueOf});
	}
}
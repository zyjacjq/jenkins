package chapter.forezp.test;

//定义一个根据工资筛选的实现类
public class EmployFilterBySalary implements EmployStrategy{
    @Override
	public boolean filterEmploy(Employ employ) {
		return  employ.getSalary() > 6000 ? true : false;
	}
}
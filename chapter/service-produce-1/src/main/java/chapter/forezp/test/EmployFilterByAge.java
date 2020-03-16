package chapter.forezp.test;

//定义一个根据年龄筛选的实现类
public class EmployFilterByAge implements EmployStrategy{
    @Override
	public boolean filterEmploy(Employ employ) {
		return  employ.getAge() > 25 ? true : false;
	}
}
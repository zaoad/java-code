import java.util.Comparator;
import java.util.*;
class student  {
    int tokens;
    String name;
    double cgpa;
    student(int tokens,String name,double cgpa)
    {
        this.tokens=tokens;
        this.name=name;
        this.cgpa=cgpa;
    }

}
class Priority implements Comparator<Object>
{

    public int compare(Object o1, Object o2)
    {
        student a1=(student)o1;
        student a2=(student)o2;
        if(a1.cgpa<a2.cgpa)
            return 1;
        else if(a1.cgpa==a2.cgpa)
        {
            if(a1.name.compareTo(a2.name)>0)
            {
                return 1;
            }
            else if(a1.name.compareTo(a2.name)==0)
            {
                if(a1.tokens>a2.tokens)
                    return 1;
                else
                    return -1;
            }
            return -1;
        }
        return -1;
    }
}
public class TaskSixSolution23 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int tokens;
        String name;
        double cgpa;
        PriorityQueue<student> queue=new PriorityQueue<student>(1005, new  Priority());
        for(int i=0;i<n;i++)
        {
            String s=sc.next();
            if(s.equals("ENTER"))
            {
                name=sc.next();
                cgpa=sc.nextDouble();
                tokens=sc.nextInt();
                queue.add(new student(tokens,name,cgpa));
            }
            if(s.equals("SERVED"))
            {
                if(!queue.isEmpty())
                queue.remove();
            }

        }
        if(queue.isEmpty())
            System.out.println("EMPTY");
        while (!queue.isEmpty())
        {
            student st=queue.peek();
            System.out.println(st.name);
            queue.remove();
        }
    }
}

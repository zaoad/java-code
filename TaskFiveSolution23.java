import java.util.*;
public class TaskFiveSolution23 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int a[]=new int[10000000];
        Arrays.fill(a,0);
        int i,ans=0,newel,max=0;
        Deque deque = new ArrayDeque(m);
        for(i=0;i<n;i++)
        {

            if(i<m)
            {
                newel=sc.nextInt();
                deque.addLast(newel);
                if(a[newel]==0) {
                    a[newel]++;
                    ans++;
                }
                else {
                    a[newel]++;
                }
                if(i==m-1)
                {
                    if(max<ans)
                        max=ans;
                }
            }
            else {
                newel=sc.nextInt();
                int el= (int) deque.getFirst();
                a[el]--;
                if(a[el]==0)
                {
                    ans--;
                }
                deque.removeFirst();
                deque.addLast(newel);
                if(a[newel]==0)
                {
                    a[newel]++;
                    ans++;
                }
                if(max<ans)
                    max=ans;
            }
        }
        System.out.println(max);
    }

}

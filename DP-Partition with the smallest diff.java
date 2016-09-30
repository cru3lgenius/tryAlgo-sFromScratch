import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner scan = new Scanner(System.in);
		System.out.println("How many testcases?");
		int testCases = Integer.parseInt(br.readLine());
		while(testCases>0)
		{
			testCases--;
			System.out.println("How many elements are in the set?");
			int numOfElements = Integer.parseInt(br.readLine());
			int arr[] = new int[numOfElements];	
			int sum =0;
			
			/* Fill the array with the desired elements 
			 * and calculate their sum
			 */
			for(int i = 0;i<numOfElements;i++)
			{
				arr[i] = scan.nextInt();
				sum+=arr[i];
			}
			
			/* Building the dynamic array:
			 * The logic behind the initialization is the following:
			 * Sum = 0 can be achieved with every kind of set even with the empty one.
			 * For this reason there is a 1 (true) in the first column of the matrix.It 
			 * doesn't matter if there are 1,2,3...etc. elements in the set you always
			 * can have sum = 0.
			 * However with 0 elements in the set you can't have sum different than 0.For
			 * this reason there is a 0 (false) for the elements in the first row
			 */
			int dynamicArr[][] = new int[numOfElements+1][sum+1];
			int i,j;
			for(i = 1;i<sum+1;i++)dynamicArr[0][i] = 0;
			for(i = 0;i<numOfElements+1;i++)dynamicArr[i][0] = 1;
			
			// Initialize the dynamic Array with the right values
			for(i = 1;i<numOfElements+1;i++)
			{
				for(j = 1;j<sum+1;j++)
				{
					/* if the sum we are searching for
					 * is already achieved with all the 
					 * elements before we can obviously achieve it 
					 * with one more element in the set too 
					 */
					dynamicArr[i][j] = dynamicArr[i-1][j];
					
					/* if the sum you are trying to achieve (j) is bigger
					 * than the value of the element you are currently at (element arr[i-1])
					 * than you should check if you can achieve it with all the elements
					 * between index 1 and i-1 (or look at that as sum of the elements arr[k],
					 * where k = 1,2,i-1; if this sum + the element you are currently at is equal to j
					 * than you should write 1 (true) in dynamicArr[i][j]. Else write 0 (false)
					 */
					if(j>=arr[i-1])
					{
						dynamicArr[i][j] = dynamicArr[i][j] | dynamicArr[i-1][j-arr[i-1]];
					}
				}
			}
			/* We initialize j  to sum/2 since we search for the minimum difference
			 * this way the first sum that we are able to find between index j and 1
			 * that is initialize to 1 is going to be the sum of one of the partitions (the smaller one).
			 * A good way to grasp the concept is to imagine that already the first position we check 
			 * dynamicArr[i][sum/2] is equal to 1 (true). This will mean that the sum of all numbers in the
			 * set is even and there is a way to make two partitions with equal sums. Link to this problem:
			 * http://www.geeksforgeeks.org/dynamic-programming-set-18-partition-problem/
			 */
			j = sum/2;
			int diff = 0;
			 
			// This loop will stop with the first sum that we can achieve and it is close to the sum/2
			while(j>=0)
			{
				if(dynamicArr[i-1][j]==1)
				{
					diff = sum - j*2;
					break;
				}
				j--;
			}
			System.out.println(diff);
		}
	}

}

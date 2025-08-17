
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Solution{
    public boolean issorted(int[]arr){
        for(int i=1;i<arr.length;i++){
            if(arr[i]>arr[i-1]){

            }
            else{
                return false;
            }
        }
        return true;
    }

    //removing dulplicates from the array
    //arr[1,1,2,2,3,4,4] array will be sorted
    //Output array [1,2,3,4]
    //Brute force could be to create a new array and simple put the unique elements in the second araay and copy it, return the size +1
    public int remdup(int[]arr){
        //optimal approach to removing duplicates from an array

        int i=0;
        for(int j=1;j<arr.length;j++){
            if(arr[j]!=arr[i]){
                arr[i+1]=arr[j];
                i++;
            }
        }
        return i+1;
    }
    public String rev_string(String str){
        String reversed=" ";
        for(int j=str.length()-1;j>=0;j--){
            reversed+=str.charAt(j);
        }
        return reversed;
    }
    public boolean isPalindrome(String Str){
        String str2=Str.toLowerCase();
        int left=0;
        int right=str2.length()-1;
        
        while(left<right){
            if(str2.charAt(right)!=str2.charAt(left)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    ///""
    public int largestelem(int[]arr){
        int largest=arr[0];
        for(int i=1;i<arr.length;i++){
            if(arr[i]>largest) largest=arr[i];

        }
        return largest;
    }
    public int smallestelem(int[]arr){
        int smallest=arr[0];
        if(arr==null || arr.length==0){
            throw new IllegalArgumentException("Array is Empty");
        }
        for(int i=1;i<arr.length;i++){
            if(arr[i]<smallest) smallest=arr[i];
        }
        return smallest;
    }

    public int[] rev_array(int[]arr){
        int start=0;
        int end=arr.length-1;
        while(start<end){
            int temp=arr[start];
            arr[start]=arr[end];
            arr[end]=temp;
            start++;
            end--;
        }
        return arr;
    }

    public int SecondLargest(int[]arr){
        int l=arr[0];
        int sl=Integer.MIN_VALUE;
        if(arr==null||arr.length<2){
            throw new IllegalArgumentException("Array should contain at least 2 elemets");
        }
        for(int i=1;i<arr.length;i++){
            if(arr[i]>l){
                sl=l;
                l=arr[i];
            }
            else if(arr[i]<l && arr[i]>sl){
                sl=arr[i];
            }
        }
        if(sl==Integer.MIN_VALUE){
            throw new  IllegalArgumentException("All elements are equal or no second largest exists");
        }
        return sl;
    }
    public int Sum_of_Array(int[]arr){
        if(arr==null){
            throw new IllegalArgumentException("No element exists in array");
        }
        
        int sum=0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
        }
        return sum;
    }
    public void Move_Zeroes(int[]arr){

        if(arr==null)throw new IllegalArgumentException("No elemets in array to move");
        int j=0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]!=0){
                arr[j]=arr[i];
                j++;
            }
        }
        while(j<arr.length){
            arr[j]=0;
            j++;
        }
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+"\t");
        }
    }
    public boolean contains_duplicate(int[]arr){
        Arrays.sort(arr);
        for(int i=0;i<arr.length-1;i++){
            if(arr[i]==arr[i+1]){
                return false;
            }
        }
        return true;
}
    static ArrayList<Integer> findUnion(int[] arr1, int[] arr2) {
    ArrayList<Integer> res = new ArrayList<>();
    int i = 0,j=0;
    //Skip the duplictes
    while(i<arr1.length && j<arr2.length){
        if(i>0&&arr1[i]==arr1[i-1]){
            i++;
            continue;
        }
        if(j>0&&arr2[j]==arr2[j-1]){
            j++;
            continue;
        }
        //compare the elements that are same and add them in the arraylist once only
        if(arr1[i]<arr2[j]){
            res.add(arr1[i++]);
        }
        else if(arr1[i]>arr2[j]){
            res.add(arr2[j++]);
        }
        else{
            res.add(arr1[i]);
            i++;
            j++;
        }
    }
    while(i<arr1.length){
        if(i==0 ||arr1[i]!=arr1[i-1]){
            res.add(arr1[i]);
        }
        i++;
    }
    while(j<arr2.length){
        if(j==0 || arr2[j]!=arr2[j-1]){
            res.add(arr2[j]);
        }
        j++;
    }
    return res;
    }

    public int[] intersection(int[] arr1, int[] arr2){
        int[] res=new int[arr1.length+arr2.length];
        int i=0;
        int j=0;
        int k=0;
        while(i<arr1.length && j<arr2.length){
            while(i>0&&i<arr1.length&&arr1[i]==arr1[i-1]){
                i++;
            }
            while(j>0&&j<arr2.length&&arr2[j]==arr2[j-1]){
                j++;
            }
            if(arr1[i]==arr2[j]){
                res[k]=arr1[i];
                i++;
                j++;
                k++;
            }
            else if(arr1[i]<arr2[j]){
                i++;
            }
            else{
                j++;
            }
         }
         return res;
    }

    public int missing_number(int[]arr, int n){
        Arrays.sort(arr);
        int sum=0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
        }
        int sum2=n*(n+1)/2;
        int missing_num=sum2-sum;
        return missing_num;
    }

public int Missing_number2(int[]arr,int n){
    int res=0;
    HashMap<Integer,Boolean> saurabh=new HashMap<>();
    for(int num:arr){
        saurabh.put(num,true);
    }
    for(int i=1;i<=n;i++){
        if(!saurabh.containsKey(i)){
            res=i;
        }
    }
    return res;
}
public int Missing_number3(int[]arr,int n){
    int xor1=0;
    int xor2=0;
    for(int i=1;i<=n;i++){
        xor1=xor1^i;
    }
    for(int i=0;i<arr.length;i++){
        xor2=xor2^arr[i];
    }
    return xor1^xor2;
}
public int consecutive_ones(int[]arr){
    int count =0;
    int max=0;
    for(int i=0;i<arr.length;i++){
        if(arr[i]==1){
            count++;
            max=Math.max(max,count);
        }
        else{
            count=0;
        }
    }
    return max;
}
    public int largest_subarray(int []arr, int k){
        HashMap<Integer,Integer>map=new HashMap<>();
        int prefix_sum=0;
        int len=0;
        map.put(0,-1);
        for(int i=0;i<arr.length;i++){
            prefix_sum+=arr[i];
            if(map.containsKey(prefix_sum-k)){
                int prev_index=map.get(prefix_sum-k);
                int length=i-prev_index;
                len=Math.max(len,length);
            }
            if(!map.containsKey(prefix_sum)){
                map.put(prefix_sum,i);
            }
        }
        return len;
    }
    public void Max_69_Number(int[]arr){
        for(int i=0;i<arr.length;i++){
            if(arr[i]==6){
                arr[i]=9;
                break;
            }
        }
        for(int d:arr){
            System.out.print(d);
        }
    }
    public void Split_String(String Str){
        String[] Str2=Str.split(" ");

        for(String st:Str2){
            System.out.print(st);
        }
    }
    public void Reverse_String(String Str){
        String[]Str2=Str.split(" ");
        for(String words:Str2){
            System.out.println(words.length());
        }

    }

}

public class Main {
    static Solution s=new Solution();
    @SuppressWarnings("ImplicitArrayToString")
    public static void main(String[] args) throws IllegalArgumentException {
        Solution s=new Solution();
        int []arr={9,9,9,9};
        String Str="Saurabh Randi hai";
        //int[]arr2={2,2,3,5};

        //String str="Hello";
        //System.out.print("Hello");
        //System.out.print(s.issorted(arr));
        s.Reverse_String(Str);
        //System.out.print(arr.length);
    }
        
}
    

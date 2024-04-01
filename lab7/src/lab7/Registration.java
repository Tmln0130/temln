package lab7;
import java.util.Scanner;
public class Registration {
	String name;
	String reg_no;
	int balance,amount_paid,fullFees,total;
	int maths,eng,kisw,scie,ss;
	char mathsg,engg,kiswg,scieg,ssg,totalg;
	public void Students_details()
	{
	Scanner in = new Scanner(System.in);
	System .out.println("Enter your name:");
	name=in.nextLine();
	System.out.println("Enter your registration number:");
	reg_no=in.nextLine();

	}
	public void fees(int fullFees)
	{
	    Scanner in = new Scanner(System.in);
	    this.fullFees=fullFees;
	    System.out.println("Enter the amount of fees paid:");
	    amount_paid=in.nextInt();
	    balance=fullFees-amount_paid;
	    if(balance==0)
	    {
	        System.out.println("You have paid your school fees in full.");
	    }
	    else if(balance>0)
	    {
	        System.out.println("You have a balance of: "+balance);
	    }
	    else
	    {
	        System.out.println("You have completed your fees payment and you have an overpayment of: "+balance);
	    }
	}
	public void kcpe_marks()
	{
	   String arr[]={"Maths","English","Kiswahili","Science","S/S and CRE"};
	  int marks[]=new int[5];
	  char grade[]=new char[5];
	  Scanner in = new Scanner(System.in);
	  boolean isrunning=false;
	  do
	  {
	  for(int i=0;i<arr.length;i++)
	  {
	      System.out.println("Enter your marks for: "+arr[i]);
	      marks[i]=in.nextInt();
	      if(marks[i]<=100&&marks[i]>=0)
	      {
	      if (marks[i]>80)
	      {
	          grade[i]='A';
	      }
	      else if(marks[i]>60)
	      {
	         grade[i]='B';  
	      }
	      else if(marks[i]>50)
	      {
	         grade[i]='C';  
	      }
	      else if(marks[i]>40)
	      {
	         grade[i]='D';  
	      }
	      else 
	      {
	         grade[i]='E';  
	      }
	      }
	      else
	       {
	          System.out.println("You have entered values not in range!! \nPlease enter the values again!!");
	          i=200;
	          isrunning=true;
	      }
	     
	  }
	  }
	  while(isrunning);
	 
	 maths=marks[0];
	 eng=marks[1];
	kisw=marks[2];
	scie=marks[3];
	ss=marks[4];
	total=marks[0]+marks[1]+marks[2]+marks[3]+marks[4];
	   if (total>=400&&total<=500)
	      {
	          totalg='A';
	      }
	      else if(total>=300)
	      {
	         totalg='B';  
	      }
	      else if(total>200)
	      {
	         totalg='C';  
	      }
	      else if(total>100)
	      {
	         totalg='D';  
	      }
	      else 
	      {
	         totalg='E';  
	      }
	mathsg=grade[0];
	 engg=grade[1];
	kiswg=grade[2];
	scieg=grade[3];
	ssg=grade[4];
	}
	public void records()
	{
	  System.out.println("Name: "+name);
	  System.out.println("Reg. No: "+reg_no);
	  System.out.println("Fees paid: "+amount_paid);
	  System.out.println("Fee balance: "+balance);
	  System.out.println(
	          "KCPE Marks: " + "Maths: " + maths +"\nGrade: "+mathsg
	                  + "\nEnglish: " + eng +" \nGrade: "+engg
	                  + "\nKiswahili: "+ kisw +" \nGrade: "+kiswg
	                  + "\nScience: " + scie +"\nGrade: "+scieg
	                  + "\nS/S and CRE: "+ ss +" \nGrade: "+ssg
	                  +"\nTotal: "+total +"\nGrade: "+ totalg
	  );
	}
	public static void main(String[] args) {
		Registration obj =new Registration();
		 int Fullfees=26000;
		 obj.Students_details();
		 obj.fees(Fullfees);
		 obj.kcpe_marks();
		 obj.records();
		

	}

}
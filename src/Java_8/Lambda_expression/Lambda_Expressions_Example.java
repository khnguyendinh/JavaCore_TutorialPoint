package Java_8.Lambda_expression;

public class Lambda_Expressions_Example {
    public static void main(String[] args) {
        Lambda_Expressions_Example lambda_expressions_example = new Lambda_Expressions_Example();
        //with type declare
        MathOperation addition = (int a, int b) -> a + b;
        //with out type declare
        MathOperation subtraction = ( a,  b) -> a - b;
        //with return statement along with curly braces
        MathOperation multiplication = (int a,int b )-> {return a*b;};
        //without return statement and without curly braces
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + lambda_expressions_example.operate(10, 5, addition));
        System.out.println("10 - 5 = " + lambda_expressions_example.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + lambda_expressions_example.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + lambda_expressions_example.operate(10, 5, division));


        //without parenthesis
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);

        //with parenthesis
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);

        greetService1.sayMessage("Mahesh");
        greetService2.sayMessage("Suresh");
    }

    interface MathOperation {
        int operation(int a, int b);
    }
    private int operate(int a, int b, MathOperation mathOperation){
        return  mathOperation.operation(a,b);
    }
    interface GreetingService {
        void sayMessage(String message);
    }
}

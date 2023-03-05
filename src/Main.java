import java.util.Scanner;

public class Main {
    //
    public static void main(String[] args) {
//        System.out.println("Hello world!");
        Scanner in = new Scanner((System.in));
        System.out.println("Введите арифметическое выражение");
        String word = in.nextLine();
        System.out.println("результат вычислений "+ calc(word));
    }
        public static String calc(String input){
        char []operandSymbols = new char [] {'+','-','*','/'};  //массив возможных операций
        String firstNum = null;
        String secondNum= null;
        String strResult = null;
        int CountSymbols=0;
        double oneNum=0;          //первое число в дабл
        double twoNum=0;          //второе число в дабл
        double result = 0;          //объявляем переменную с результатами
            char operandSym='+';                                //объявление символа операнда, вычисляемого ниже
            char firstSym = input.charAt(0);                 //порверка араб/римск/невалид значение первого символа
            char lastSym = input.charAt(input.length()-1);    //порверка араб/римск/невалид значение последнего символа
            //boolean arabRoma= true;       //TRUE- арабские FALSH- римские
        for (int i=0; i<input.length();i++)
        {
            for (int k=0; k<operandSymbols.length;k++)
            {
                if (input.charAt(i)==operandSymbols[k])
                    CountSymbols++;
            }
        }
        // System.out.println("CountSymbols  "+CountSymbols);
        if (CountSymbols==0) {        // проверка случая <1>
            System.out.println("throws Exception  в вводе отсутствует арифметический оператор");
            strResult= "throws Exception в вводе отсутствует арифметический оператор";
        }
        else{
            if (CountSymbols>1) {        // // проверка случая <1+2+3>
                System.out.println("throws Exception  в вводе больше одного арифметического оператора");
                strResult= "throws Exception  в вводе больше одного арифметического оператора";
            }
            else {
//        char firstSym = input.charAt(0);                 //порверка араб/римск/невалид значение первого символа
//        char lastSym = input.charAt(input.length()-1);    //порверка араб/римск/невалид значение последнего символа
        if(Math.abs(ArabRomulSymbol(firstSym)+ArabRomulSymbol(lastSym))<2){    //проверка символов на соответсвие одной системе: если обе арабские то 1+1=2 если обе римские то -1+(-1)=-2
            System.out.println("throws Exception");
            strResult= "throws Exception";
        }
        else {                                          //если исходные данные валидны то продолжаем алгоритм

            int operandIndex = 4;                             //Присвоение ИНвалидного номера индексу операторв , как флаг того что символ операнда не выбран(для выхода из цикла)
            for (int i = 0; i < (input.length() - 1); i++)         //Цикл, перебирающий все символы входного слова в поиске символа операции
            {
                for (int j = 0; j < operandSymbols.length; j++)//
                {
                    if (input.charAt(i) == operandSymbols[j]) //если символ найден, то
                    {
                        operandSym = operandSymbols[j];
                        operandIndex = j;                 //символу операции присваивается индекс по которому потом выполняется действие
                        firstNum = input.substring(0, i);       //первое число(пока String) от начала до символа операции
                        secondNum = input.substring(i + 1);    //второе число(пока String) от  символа операции до конца
                        break;
                    }
                }
                if (operandIndex != 4) {                     //если символ  найден,и operandIndex валидный(от 0 до 3), то выход из цикла
                    break;
                }
            }
            //int result=ArabRomulSymbol(firstSym)+ArabRomulSymbol(lastSym);    // МУ -использовал для проверки кода
            //System.out.println("throws  NO Exception: "+result);              // МУ -использовал для проверки кода
//            double oneNum;          //первое число в дабл
//            double twoNum;          //второе число в дабл
 //           double result = 0;          //объявляем переменную с результатами
            if (ArabRomulSymbol(firstSym) < 0)    //если числа римские то переводим их в арабские
            {
                assert firstNum != null;
                oneNum = RomToArab(firstNum);
                twoNum = RomToArab(secondNum);
//                System.out.println("roma first num " + oneNum);   // МУ -использовал для проверки кода
//                System.out.println("roma second num " + twoNum); // МУ -использовал для проверки кода
            } else                                //если арабские то просто переводим Стринг в дабл
            {
                oneNum = Double.parseDouble(firstNum);
                twoNum = Double.parseDouble(secondNum);
//                System.out.println("arab first num " + oneNum);   // МУ -использовал для проверки кода
//                System.out.println("arab second num " + twoNum); // МУ -использовал для проверки кода
            }

//            System.out.println("номер операнда  " + operandIndex);    // МУ -использовал для проверки кода
            switch (operandIndex) {
                case 0:
                    result = oneNum + twoNum;
                    break;
                case 1:
//                    if (oneNum < twoNum && (ArabRomulSymbol(firstSym) + ArabRomulSymbol(lastSym)) < 0) {
////                        System.out.println((ArabRomulSymbol(firstSym) + ArabRomulSymbol(lastSym)));
//                        System.out.println(" в римских числах нет отрицательных чисел");
//                        strResult = "throws Exception";
//                    }
//                    else {
                    result = oneNum - twoNum;
//                    }
                    break;
                case 2:
                    result = oneNum * twoNum;
                    break;
                case 3:
                    result = oneNum / twoNum;
            }
            if ((ArabRomulSymbol(firstSym) + ArabRomulSymbol(lastSym)) < 0) {
//                System.out.println("результат римские " + ConvArabToRoma(result));
//                System.out.println(ConvArabToRoma(result));
                strResult=ConvArabToRoma((int)result);
                //arabRoma=true;
            }
            else {
                //System.out.println(result);
                strResult=""+(int)result;
                //arabRoma=false;
            }
        }
        }
        }
        if (oneNum<1|oneNum>10|twoNum<1|twoNum>10) {
            strResult = "throws Exception";
            System.out.println(" входные данные не в диапазоне {1-10}");
        }
                if ((oneNum - twoNum)<0 && operandSym == '-') {
                       System.out.println(" в римских числах нет отрицательных чисел");
                        strResult = "throws Exception";
                   }
            return strResult;
    }
    public static double RomToArab(String word)     /// метод переводящий римские цифры в арабские
    {
        int sumA=ConvRomNum(word.charAt(word.length()-1));  //присвоение первого справа  символа сумме(гарантировано наличие)
//        System.out.println("first number  "+sumA);
        int a=0;                                            //текущая цифра римского числа
        int b=sumA;                                            //предидущая цифра римского числа
        for (int i = word.length()-2;i>=0;i--)
        {
            a=ConvRomNum(word.charAt(i)); //вычисление следующей цифры в римском числе
            //b=ConvRomNum(word.charAt(i-1)); //вычисление предидущей цифры в римском числе
//            System.out.println("предидущая цифра "+ b);
//            System.out.println("текущая цифра "+ a);
            if (a<b)
                sumA=sumA-a;
            else sumA=sumA+a;
            b=a;
//            System.out.println(sumA);
        }
        return sumA;
    }
    public static int ConvRomNum(char abb)
    {
        int a=0;
        switch (abb)
        {
            case 'I':
                a=1;
                break;
            case 'V':
                a=5;
                break;
            case 'X':
                a=10;
                break;
            case 'L':
                a=50;
                break;
            case 'C':
                a=100;
                break;
            case 'D':
                a=500;
                break;
            case 'M':
                a=1000;
                break;
        }
        return a;
    }
    public static int ArabRomulSymbol(char a)       /// метод устанавливающий флаг римс/араб/невалид
    {
        int flag =0;
        // System.out.println("a "+ a );                    //// МУ -использовал для проверки кода
        for (int i=0; i<10; i++)
        {
            if (a == Character.forDigit(i, 10))
            {
                flag =1;
                //System.out.println("flag "+ flag );       // МУ -использовал для проверки кода
                return flag;
            }
        }
        if (a == 'I'||a =='V'||a =='X'||a =='C'||a =='M'||a =='L')
        {
            flag =-1;
            // System.out.println("flag "+ flag );          // МУ -использовал для проверки кода
            return flag;
        }
        return flag;
    }
    public static String ConvArabToRoma(double a)
    {
          String RomaNum="";
          String inputA=String.valueOf(Math.round(a));
        while (inputA.length()<4){
            inputA="0"+inputA;
        }
//        System.out.println("inputA   " +inputA);
        for (int j= 0; j<inputA.length(); j++)
        {
 //           int j =inputA.length()-1-i;
            switch (j)
            {
                case 0:
                {
                    switch (inputA.charAt(j)) {
                        case '3':
                            RomaNum = RomaNum + "MMM";
                            break;
                        case '2':
                            RomaNum = RomaNum + "MM";
                            break;
                        case '1':
                            RomaNum = RomaNum + "M";
                            break;
                    }
                    break;
                }
                case 1:
                {
                    switch (inputA.charAt(j))
                    {
                        case '9':
                            RomaNum=RomaNum+"CM";break;
                        case '8':
                            RomaNum=RomaNum+"DCCC";break;
                        case '7':
                            RomaNum=RomaNum+"DCC";break;
                        case '6':
                            RomaNum=RomaNum+"DC";break;
                        case '5':
                            RomaNum=RomaNum+"D";break;
                        case '4':
                            RomaNum=RomaNum+"CD";break;
                        case '3':
                            RomaNum=RomaNum+"CCC";break;
                        case '2':
                            RomaNum=RomaNum+"CC";break;
                        case '1':
                            RomaNum=RomaNum+"C";break;
                    }
                    break;
                }
                case 2:
                {
                    switch (inputA.charAt(j))
                    {
                        case '9':
                            RomaNum=RomaNum+"XC";break;
                        case '8':
                            RomaNum=RomaNum+"LXXX";break;
                        case '7':
                            RomaNum=RomaNum+"LXX";break;
                        case '6':
                            RomaNum=RomaNum+"LX";break;
                        case '5':
                            RomaNum=RomaNum+"L";break;
                        case '4':
                            RomaNum=RomaNum+"XL";break;
                        case '3':
                            RomaNum=RomaNum+"XXX";break;
                        case '2':
                            RomaNum=RomaNum+"XX";break;
                        case '1':
                            RomaNum=RomaNum+"X";break;
                    }
                    break;
                }
                case 3:
                {
                    switch (inputA.charAt(j))
                    {
                        case '9':
                            RomaNum=RomaNum+"IX";break;
                        case '8':
                            RomaNum=RomaNum+"VIII";break;
                        case '7':
                            RomaNum=RomaNum+"VII";break;
                        case '6':
                            RomaNum=RomaNum+"VI";break;
                        case '5':
                            RomaNum=RomaNum+"V";break;
                        case '4':
                            RomaNum=RomaNum+"IV";break;
                        case '3':
                            RomaNum=RomaNum+"III";break;
                        case '2':
                            RomaNum=RomaNum+"II";break;
                        case '1':
                            RomaNum=RomaNum+"I";break;
                    }
                    break;
                }
            }
//            System.out.println(RomaNum);
        }
        return RomaNum;
    }
}

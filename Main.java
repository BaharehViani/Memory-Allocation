import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main( String[] args )
    {
        ArrayList<Integer> pages = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        System.out.println("enter memory size:");
        int memorySize = scan.nextInt();
        System.out.println("enter pages size: (enter q to exit)");
        while (true) {
            String str = scan.next();
            if (str.equals("q")) {
                break;
            }
            pages.add(Integer.valueOf(str));
        }
        System.out.println("enter w for worst fit, b for best fit and f for first fit");
        String choice = scan.next();
        if (choice.equals("w")) {
            worstFit(pages, memorySize);
        } else if (choice.equals("b")) {
            bestFit(pages, memorySize);
        } else {
            firstFit(pages, memorySize);
        }
        scan.close();
    }

    private static void worstFit(ArrayList<Integer> pages, int memorySize) {
        Scanner scan = new Scanner(System.in);
        int externalFragmentation = 0;
        int internalFragmentation = 0;
        int full = 0;
        for (int i = 0; i < pages.size(); i++) {
            full += pages.get(i);
        }
        System.out.println("enter processes size: (enter q to exit)");
        while (true) {
            if (pages.size() == 0) {
                System.out.println("no more pages are available");
                break;
            }
            String str = scan.next();
            if (str.equals("q")) {
                break;
            }
            int size = Integer.valueOf(str); 
            int max = Integer.MIN_VALUE;
            int index = 0;
            boolean found = false;
            for (int i = 0; i < pages.size(); i++) {
                if (size <= pages.get(i) && pages.get(i) > max) {
                    max = pages.get(i);
                    index = i;
                    found = true;
                }
            }
            if (!found) {
                System.out.println("process size is bigger than all avaliable pages");
                continue;
            }
            System.out.println("page with size " + pages.get(index) + " is allocated to process");
            internalFragmentation += pages.get(index) - size; 
            pages.remove(index);
        }
        scan.close();
        for (int i = 0; i < pages.size(); i++) {
            internalFragmentation += pages.get(i);
        }
        externalFragmentation = memorySize - full;
        System.out.println("internal fragmentation = " + internalFragmentation);
        System.out.println("external fragmentation = " + externalFragmentation);
    }

    private static void bestFit(ArrayList<Integer> pages, int memorySize) {
        Scanner scan = new Scanner(System.in);
        int externalFragmentation = 0;
        int internalFragmentation = 0;
        int full = 0;
        for (int i = 0; i < pages.size(); i++) {
            full += pages.get(i);
        }
        System.out.println("enter processes size: (enter q to exit)");
        while (true) {
            if (pages.size() == 0) {
                System.out.println("no more pages are available");
                break;
            }
            String str = scan.next();
            if (str.equals("q")) {
                break;
            }
            int size = Integer.valueOf(str); 
            ArrayList<Integer> bigger = new ArrayList<>();
            boolean found = false;
            for (int i = 0; i < pages.size(); i++) {
                if (size <= pages.get(i) ) {
                    bigger.add(pages.get(i));
                    bigger.add(i);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("process size is bigger than all pages");
                continue;
            }
            int min = Integer.MAX_VALUE;
            int index = 0;
            for (int i = 0; i < bigger.size(); i+=2) {
                if (bigger.get(i) < min) {
                    min = bigger.get(i);
                    index = bigger.get(i+1);
                }
            }
            System.out.println("page with size " + pages.get(index) + " is allocated to process");
            internalFragmentation += pages.get(index) - size; 
            pages.remove(index);
        }
        scan.close();
        for (int i = 0; i < pages.size(); i++) {
            internalFragmentation += pages.get(i);
        }
        externalFragmentation = memorySize - full;
        System.out.println("internal fragmentation = " + internalFragmentation);
        System.out.println("external fragmentation = " + externalFragmentation);
    }

    private static void firstFit(ArrayList<Integer> pages, int memorySize) {
        Scanner scan = new Scanner(System.in);
        int externalFragmentation = 0;
        int internalFragmentation = 0;
        int full = 0;
        for (int i = 0; i < pages.size(); i++) {
            full += pages.get(i);
        }
        System.out.println("enter processes size: (enter q to exit)");
        while (true) {
            if (pages.size() == 0) {
                System.out.println("no more pages are available");
                break;
            }
            String str = scan.next();
            if (str.equals("q")) {
                break;
            }
            int size = Integer.valueOf(str); 
            boolean found = false;
            for (int i = 0; i < pages.size(); i++) {
                if (size <= pages.get(i)) {
                    System.out.println("page with size " + pages.get(i) + " is allocated to process");
                    internalFragmentation += pages.get(i) - size; 
                    pages.remove(i);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("process size is bigger than all pages");
                continue;
            }
        }
        scan.close();
        for (int i = 0; i < pages.size(); i++) {
            internalFragmentation += pages.get(i);
        }
        externalFragmentation = memorySize - full;
        System.out.println("internal fragmentation = " + internalFragmentation);
        System.out.println("external fragmentation = " + externalFragmentation);
    }
}
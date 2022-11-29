package javaTool.lib.UI;

import java.util.*;

import javaTool.lib.terminal.ColorTerminal;
import javaTool.lib.terminal.ColorTerminal.COLOR;
import javaTool.lib.util.MyObj;

/**
*
*
*/
public class MyList {

    public static ArrayList<?> listToSet(ArrayList<?> list) {
        return list.isEmpty() ? null : new ArrayList<>(Arrays.asList(new HashSet<>(list)));
    }

    public static void printList(ArrayList<?> list) {
        if (list.isEmpty()) {
            System.out.println("[]");
            return;
        }
        list.forEach(each -> System.out.println(MyObj.getInfo(each)));
    }

    public static void printList(Class<?> list[]) {
        printList(new ArrayList<>(Arrays.asList(list)));
    }

    private static int getMaxStrLength(ArrayList<String> list) {
        int max = list.get(0).length();
        for (String each : list)
            max = Math.max(each.length(), max);

        return max;
    }

    private static boolean hasEmptyStr(ArrayList<String> list) {
        for (String each : list)
            if (each.length() == 0)
                return true;

        return false;
    }

    private static ArrayList<String> getAllData(ArrayList<?> list) {
        ArrayList<String> allData = new ArrayList<>();
        list.forEach(each -> allData.addAll(MyObj.getInfo(each)));

        return allData;
    }

    public static void printTable(ArrayList<?> list, Object... renameAttribute) {
        printTable("Untitled", list, renameAttribute);
    }

    public static void printTable(String tableName, ArrayList<?> list, Object... renameAttribute) {
        if (list.isEmpty()) {
            System.out.println("[]");
            return;
        }

        ArrayList<String> atbList = MyObj.getAttribute(list.get(0));

        tableName = tableName.isEmpty() ? "Untitled" : tableName;

        if (atbList.isEmpty()) {
            atbList.add("null");
            tableName = "empty table";
        }

        if (renameAttribute != null) {
            ArrayList<Integer> renameIdx = new ArrayList<>();
            ArrayList<String> renameCol = new ArrayList<>();

            for (Object each : renameAttribute)
                if (each instanceof Integer)
                    renameIdx.add((int) each);
                else
                    renameCol.add(String.valueOf(each));

            int j = 0;
            for (int i : renameIdx)
                atbList.set(i, renameCol.get(j++));
        }

        int numOfAtb = atbList.size();
        int dataLength = getMaxStrLength(getAllData(list));
        int atbLength = getMaxStrLength(atbList);

        int strLength = Math.max(dataLength, atbLength);
        if (strLength <= 4)
            if (hasEmptyStr(getAllData(list)))
                strLength = 4;
            else if (numOfAtb % 2 != 0 && strLength % 2 != 0)
                strLength++;
        strLength++;

        int idxLength = String.valueOf(list.size()).length();

        int tableLength = 0;
        int lineLength = 1;
        int nameLength = tableName.length();
        int max = Math.max(atbLength, dataLength);
        if (nameLength % 2 == 0) {
            System.out.println("name%2=0");
            if (max % 2 != 0) { // checked
                System.out.println("max%2!=0");
                if (max >= 4) {
                    System.out.println("max>=4");
                    if (idxLength <= 3) {
                        System.out.println("idx<=3");
                        idxLength = 3;
                    } else {
                        System.out.println("idx>3");
                        strLength = max + 1;
                        idxLength++;
                        tableLength++;
                    }
                } else {
                    System.out.println("max<4");
                }
            } else {
                System.out.println("max%2!=0");
                if (max >= 4) {
                    System.out.println("max>=4");
                    if (idxLength <= 3) {
                        System.out.println("idx<=3");
                        idxLength++;
                        tableLength = 1;
                    } else {
                        System.out.println("idx>3");
                    }
                } else {
                    System.out.println("max<4");
                }
            }
        } else {
            System.out.println("name%2!=0");
            if (max >= 4) {
                System.out.println("max>=4");
                if (idxLength <= 3) {
                    System.out.println("idx<=3");
                    if (max % 2 == 0) {
                        System.out.println("max%2=0");
                        if (idxLength < 3) {
                            System.out.println("idx<3");
                            idxLength = 2;
                        } else {
                            idxLength = 3;
                        }
                    } else {
                    }
                }
            } else {
                System.out.println("max<4");
                if (idxLength <= 3) {
                    System.out.println("idx<=3");
                    if (max % 2 == 0) {
                        System.out.println("max%2=0");
                        if (idxLength < 3) {
                            System.out.println("idx=1|2");
                            idxLength = 2;
                            tableLength++;
                        } else {
                            System.out.println("idx>1|2");
                            // idxLength = 3;
                            // tableLength += idxLength + (numOfAtb * strLength) + numOfAtb + 2;

                        }
                    } else {
                        System.out.println("max%2!=0");
                        idxLength++;
                        tableLength = 1;
                    }
                } else {
                    System.out.println("idx>3");
                    strLength += 2;
                    idxLength++;
                }
            }
        }

        tableLength += idxLength + (numOfAtb * strLength) + numOfAtb + 2;
        // tableLength = (tableLength % 2 != 0 || idxLength % 2 != 0) ? tableLength - 1
        // : tableLength;

        // Title Bar
        lineLength = tableLength;

        System.out.println("Max        : " + max);
        System.out.println("NameLength : " + nameLength);
        System.out.println("DataLength : " + dataLength);
        System.out.println("AtbLength  : " + atbLength);
        System.out.println("IdxLength  : " + idxLength);
        System.out.println("TableLength: " + tableLength);
        System.out.println("StrLength  : " + strLength);
        System.out.println("LineLength : " + lineLength);

        if (tableName.length() > tableLength) {
            lineLength += 6;
            strLength = 1 + (lineLength - ((idxLength + 1) + numOfAtb)) / numOfAtb;
            System.out.println("long name's StrLength: " + strLength);
            System.out.print("===" + tableName.toUpperCase() + "===");
        } else {
            int start = 0;
            // tableLength += 1;

            for (int i = start; i < tableLength; i++) {
                if (i == (tableLength / 2) - (nameLength / 2)) {
                    System.out.print(tableName.toUpperCase());
                    i += nameLength;
                }
                System.out.print("=");
            }
        }

        // Name Col
        System.out.format("\n|%-" + (idxLength + 1) + "s%s", "No.", "|");
        for (String each : atbList)
            ColorTerminal.format("%-" + strLength + "s%s", each, "|", COLOR.YELLOW_BOLD_BRIGHT);
        // other line
        String line = "";
        for (int i = 0; i < lineLength; i++)
            line += "-";
        System.out.println("\n" + line);

        for (int i = 0; i < list.size(); i++) {
            System.out.print("|");
            ColorTerminal.format("%-" + idxLength + "s.%s", i, "|", COLOR.CYAN_BOLD_BRIGHT);

            String head = "%-";
            for (String each : MyObj.getInfo(list.get(i)))
                ColorTerminal.format(head + strLength + "s%s", each.isEmpty() ? "null" : each, "|",
                        each.isEmpty() ? COLOR.RED_BOLD_BRIGHT : COLOR.RESET);

            System.out.println();
        }

        System.out.println(line.replaceAll("-", "="));
    }
}

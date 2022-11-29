package javaTool.lib.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javaTool.lib.terminal.ColorTerminal;
import javaTool.lib.terminal.ColorTerminal.COLOR;

/**
 * {@code MyList} là {@code ArrayList} dùng để đọc file
 */
public class MyFile<T> extends ArrayList<T> {
    private String filePath;

    public MyFile() {
    }

    public MyFile(String filePath) {
        this.filePath = filePath;
        this.getFile(filePath);
    }

    public void update(ArrayList<T> list) {
        this.removeAll(this);
        this.addAll(list);
    }

    public void update(String filePath) {
        this.removeAll(this);
        this.getFile(filePath);
    }

    public static void setFile(String filePath, ArrayList<?> dataList) {
        try {
            FileOutputStream fis = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fis);

            oos.writeInt(dataList.size());
            for (int i = 0; i < dataList.size(); i++)
                oos.writeObject(dataList.get(i));

            oos.close();

            System.out.println("Saved " + ColorTerminal.setColorString("" + dataList.size(), COLOR.CYAN_BRIGHT)
                    + " item(s) to " + ColorTerminal.setColorString(filePath, COLOR.CYAN_BRIGHT));
        } catch (Exception e) {
            System.out.println("cannot save to " + ColorTerminal.setColorString(filePath, COLOR.CYAN_BRIGHT));
            System.out.println("Due to: " + e);
        }
    }

    public void setFile(String filePath) {
        setFile(filePath, this);
    }

    public void setFile() {
        setFile(filePath, this);
    }

    @SuppressWarnings("unchecked")
    public void getFile(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream oos = new ObjectInputStream(fis);
            T obj;
            int length = oos.readInt();
            for (int i = 0; i < length; i++) {
                obj = (T) oos.readObject();
                this.add(obj);
            }

            System.out.println("Saved " + ColorTerminal.setColorString("" + this.size(), COLOR.CYAN_BRIGHT)
                    + " item(s) to " + ColorTerminal.setColorString(filePath, COLOR.CYAN_BRIGHT));

            oos.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("cannot save to " + ColorTerminal.setColorString(filePath, COLOR.CYAN_BRIGHT));
            System.out.println("Due to: " + e);
        }
    }

    public static ArrayList<String> getLineFromFile(String filePath) {
        ArrayList<String> list = new ArrayList<>();
        try {
            File f = new File(filePath);

            if (!f.exists())
                return null;

            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);

            String detail;
            while ((detail = br.readLine()) != null)
                list.add(detail);

            fr.close();
            br.close();
        } catch (IOException e) {
            System.out.println("cannot save to " + ColorTerminal.setColorString(filePath, COLOR.CYAN_BRIGHT));
            System.out.println("Due to: " + e);
        }

        return list;
    }

    public static void setLineToFile(String filePath, ArrayList<?> list) {
        File f = new File(filePath);
        FileWriter fw;
        try {
            fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);

            list.forEach(each -> pw.println(String.valueOf(each)));

            fw.close();
            pw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static ArrayList<String> getSubData(String filePath, String splitPattern, int... index) {
        ArrayList<String> list = getLineFromFile(filePath);
        ArrayList<String> aList = new ArrayList<>();

        list.forEach(each -> {
            each = each.toLowerCase();
            String fileName[] = each.split(splitPattern);
            for (int i : index)
                aList.add(fileName[i]);
        });

        return aList;
    }
}

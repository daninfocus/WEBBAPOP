package Modelo;

import DAO.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * The type Gestion app.
 */
public class GestionAPP implements Serializable {

    static final DAOManager dao = DAOManager.getSinglentonInstance();
    static final UsuarioSQL daoUsuarioSQL = new UsuarioSQL();
    static final ProductoSQL daoProductoSQL = new ProductoSQL();
    static final TratoSQL tratoSQL = new TratoSQL();
    static final MessageSQL daoMessageSQL = new MessageSQL();
    private File propertiesFile;
    private Properties props;

    /* Constructor */
    public GestionAPP() {
        propertiesFile = new File("/resources/properties.prop");
        props = new Properties();

        try {
            if (!propertiesFile.exists()) {
                propertiesFile.createNewFile();
                props.setProperty("invitado", "false");
                props.setProperty("lastAccess", "./src/main/resources/LastAccess.prop");
                props.setProperty("log", "./src/main/resources/log.log");
                props.setProperty("admin", "daniel.webb@fernando3martos.com");
                props.store(new FileWriter(propertiesFile), "Properties");
            }

            props.load(new FileReader(propertiesFile));

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dao.open();
            System.out.println("Conexión establecida");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error de conexión en la BBDD");
        }
    }

    /*Getter and Setter*/

    public void setProperties(File properties) {
        this.propertiesFile = properties;
    }

    public File getPropertiesFile() {
        return this.propertiesFile;
    }

    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

    /*Otros Métodos*/

    public boolean updateMessage(Message message){
        return daoMessageSQL.update(message,dao);
    }
    public boolean saveMessage(Message message) {
        return daoMessageSQL.insert(message, dao);
    }

    public ArrayList<Message> getAllMessages(int idUser, int idProducto) {
        return daoMessageSQL.readMessages(idUser, idProducto, dao);
    }

    public ArrayList<Integer> getAllChats(int idUser){
        return daoMessageSQL.getAllChats(idUser,dao);
    }
    public boolean borrarUsuario(int idUsuario) {
        return daoUsuarioSQL.delete(idUsuario, dao);
    }

    public double notaMedia(String email) {
        float total = 0;
        int contador = 0;

        Usuario userTemp = getUsuarioPorEmail(email);
        int idUsuario = userTemp.getId();

        for (Trato v : getVentas(idUsuario)) {
            if (v.getPuntuacion() >= 0) {
                total += v.getPuntuacion();
                contador++;
            }
        }

        for (Trato c : getCompras(idUsuario)) {
            if (c.getPuntuacion() >= 0) {
                total += c.getPuntuacion();
                contador++;
            }
        }

        if (contador == 0) return 0;
        double media = total / contador;
        userTemp.setNotaMedia(media);
        return media;
    }

    public boolean updateUsuario(Usuario usuario) {
        return daoUsuarioSQL.update(usuario, dao);
    }

    public int generaIdTrato() {

        int id = (int) (Math.random() * 8999 + 1000);

        for (Usuario usuario : daoUsuarioSQL.getAll(dao)) {
            for (Trato compra : getCompras(usuario.getId())) {
                if (compra.getId() == id) {
                    id = (int) (Math.random() * 8999 + 1000);
                }
            }
            for (Trato venta : getVentas(usuario.getId())) {
                if (venta.getId() == id) {
                    id = (int) (Math.random() * 8999 + 1000);
                }
            }
        }

        return id;
    }

    public boolean updateTrato(Trato trato) {
        return tratoSQL.update(trato, dao);
    }

    public Trato buscaTratoId(int id) {
        return tratoSQL.read(id, dao);
    }

    public ArrayList<Trato> getValoracionesPendientes(Usuario usuario) {
        ArrayList<Trato> tratos = tratoSQL.readPendiente(usuario.getId(), dao);
        ArrayList<Trato> temp = new ArrayList<>();
        for (Trato trato : tratos) {
            if (trato.getCompletado() == 0) {
                temp.add(trato);
            }
        }
        return temp;
    }

    public ArrayList<Producto> buscaProductoTexto(String nombreProducto) {
        ArrayList<Producto> productos = getProductos();
        ArrayList<Producto> temp = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getNombre().toLowerCase().contains(nombreProducto.toLowerCase())) {
                temp.add(producto);
            }
        }
        return temp;
    }

    public boolean updateProducto(Producto producto) {

        return daoProductoSQL.update(producto, dao);

    }

    public boolean addTrato(Trato trato) {
        return tratoSQL.insert(trato, dao);
    }

    public ArrayList<Trato> getCompras(int idUsuario) {
        ArrayList<Trato> tratos = tratoSQL.getAll(idUsuario, dao);
        ArrayList<Trato> compras = new ArrayList<>();
        for (Trato trato : tratos) {
            if (trato.getTipoTrato().equals("Compra")) compras.add(trato);
        }
        return compras;
    }

    public ArrayList<Trato> getVentas(int idUsuario) {
        ArrayList<Trato> tratos = tratoSQL.getAll(idUsuario, dao);
        ArrayList<Trato> ventas = new ArrayList<>();
        for (Trato trato : tratos) {
            if (trato.getTipoTrato().equals("Venta")) ventas.add(trato);
        }
        return ventas;
    }

    public boolean quitaProducto(int idProducto) {
        return daoProductoSQL.delete(idProducto, dao);
    }

    public Usuario buscaMail(String email) {
        return daoUsuarioSQL.read(email, dao);
    }

    public Usuario getUsuarioPorEmail(String email) {
        return daoUsuarioSQL.read(email, dao);
    }

    public Producto getProductoPorID(int id) {
        return daoProductoSQL.read(id, dao);
    }

    public ArrayList<Producto> getProductosDeUsuario(int idUsuario) {
        ArrayList<Producto> temp = new ArrayList<>();
        for (Producto producto : daoProductoSQL.getAllFromUser(idUsuario, dao)) {
            if (producto.getVendido() == 0) temp.add(producto);
        }
        return temp;
    }

    public ArrayList<Producto> getProductos() {
        return daoProductoSQL.getAll(dao);
    }

    public void addProducto(Producto producto) {
        daoProductoSQL.insert(producto, dao);
    }

    public boolean checkDBConn() {
        try {
            dao.open();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addUsuario(Usuario usuario) {

        if (daoUsuarioSQL.insert(usuario, dao)) {
            return true;
        }
        return false;
    }

    public Usuario getUsuarioPorId(int id){
        return daoUsuarioSQL.readID(id,dao);
    }

    public ArrayList<Usuario> getUsuarios() {
        if (daoUsuarioSQL.getAll(dao).size() != 0) {
            return daoUsuarioSQL.getAll(dao);
        }
        return null;
    }

    public boolean crearYEnviarExcel() {


        //Crear libro de trabajo en blanco
        Workbook workbook = new HSSFWorkbook();

        //Crea hoja nueva
        Sheet sheet = workbook.createSheet("Hoja de usuarios");

        //Por cada línea se crea un arreglo de objetos (Object[])
        Map<String, Object[]> datos = new TreeMap<String, Object[]>();
        int conta = 1;
        for (Usuario usuario : getUsuarios()) {
            datos.put(String.valueOf(conta), new Object[]{usuario.getId(), usuario.getNombre(), usuario.getEmail()});
            conta++;
        }
        //Iterar sobre datos para escribir en la hoja
        Set keyset = datos.keySet();
        int numeroRenglon = 0;
        for (Object key : keyset) {
            Row row = sheet.createRow(numeroRenglon++);
            Object[] arregloObjetos = datos.get(key);
            int numeroCelda = 0;
            for (Object obj : arregloObjetos) {
                Cell cell = row.createCell(numeroCelda++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer) obj);
                }
            }
        }

        try {
            //Se genera el documento
            File file = new File("./src/main/resources/Usuarios.xlsx");

            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();


            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getProperties() {

        String data = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(propertiesFile));
            String linea = br.readLine();
            String clave = "";
            String valor = "";
            data += "|----------------------------------------------|\n";
            data += "|    Clave      |             Valor            |\n";
            data += "|----------------------------------------------|\n";
            while (linea != null) {
                if (!linea.startsWith("#")) {
                    int num = linea.indexOf('=');
                    valor = linea.substring(0, num);
                    clave = linea.substring(num + 1, linea.length());
                    String formatted = String.format("%-15s %s \t %-5s", valor, "|", clave);

                    data += formatted + "\n";
                }
                linea = br.readLine();
            }

            br.close();
        } catch (FileNotFoundException e) {
            data = "";
        } catch (IOException e) {
            data = "";
        }

        return data;
    }

    public String getLastAccess(Usuario user) {

        Properties propsLastAccess = new Properties();
        File file = new File(props.getProperty("lastAccess"));
        String date = "no-login";
        try {
            propsLastAccess.load(new FileReader(file));
            date = propsLastAccess.getProperty(user.getEmail());
        } catch (IOException e) {
            return date;
        }
        return date;
    }

    public void setLastAccess(Usuario user, String date) {
        Properties propsLastAccess = new Properties();
        File file = new File(props.getProperty("lastAccess"));


        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            propsLastAccess.load(new FileReader(file));
            propsLastAccess.setProperty(user.getEmail(), date);
            propsLastAccess.store(new FileWriter(file), "Last Access");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void log(String mensaje, Usuario usuario, String date) {

        try {
            props.load(new FileReader(propertiesFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fecha = "";
        String hora = "";
        try {
            for (int i = 0; i < date.length(); i++) {
                if (date.charAt(i) == ' ') {
                    fecha = date.substring(0, i);
                    hora = date.substring(i + 1);
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(props.getProperty("log"), true));
            bw.write(mensaje + ";" + usuario.getEmail() + ";" + fecha + ";" + hora);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void addMock() {
        Usuario u1 = new Usuario(2, "Jose Manuel", "Gonzalez", "Adv España", "655687492", "Hombre", "josemanuelg.a23@gmail.com", "23/05/2000", "Jose12345", -1);
        //Usuario u2 = new Usuario(0, "Dani", "Webb", "C/ Jaen", "722536987", "Hombre", "daniel.webb@fernando3martos.com", "25/02/1997", "Dani12345",-1);
        //Usuario u3 = new Usuario(3, "admin", "", "", "", "", "admin", "", "admin",-1);

        //Producto p1 = new Producto(0, "Iphone 12", "Iphone 12 casi nuevo con 2 fundas", "Telefonía Móvil", 800, LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString(), "Casi Nuevo", u1.getId(), 0);
        //Producto p2 = new Producto(0, "Ratón inalámbrico Logitech", "Ratón Logitech G603 con caja original", "Informática", 25, LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString(), "Nuevo", u1.getId(), 0);
        //Producto p3 = new Producto(0, "Portátil MSI GL 65 Leopard", "Portátil MSI perfecto para jugar", "Informática", 950, LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString(), "Nuevo", u2.getId(), 0);
        //Producto p4 = new Producto(0, "Portátil MackBook Air Pro", "Portátil MackBook para trabajar", "Informática", 800, LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString(), "Casi Nuevo", u2.getId(), 0);
        //Producto p5 = new Producto(0, "Volkswagen Golf GTI", "Golf GTI 2.0 TSI rojo", "Motor", 25000, LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString(), "Casi Nuevo", u3.getId(), 0);
        //Producto p6 = new Producto(0, "Google Pixel 4 XL", "Movil Google Pixel 4 XL sin abrir", "Telefonía Móvil", 440, LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString(), "Nuevo", u3.getId(), 0);

        addUsuario(u1);
        //addUsuario(u2);
        //addUsuario(u3);

        //addProducto(p1);
        //addProducto(p2);
        //addProducto(p3);
        //addProducto(p4);
        //addProducto(p5);
        //addProducto(p6);

    }

    public Usuario login(String email, String password) {
        Usuario u = daoUsuarioSQL.read(email, dao);
        if (u != null) {
            if (u.getPassword().equals(password)) return u;
        }
        return null;
    }

    public static boolean validateString(String string) {
        boolean check = false;
        String abc = "abcdefghijklmnñopqrstuvwxyzáéíóú. ";
        for (int i = 0; i < string.length(); i++) {
            check = false;
            for (int j = 0; j < abc.length(); j++) {
                if (string.toLowerCase().charAt(i) == abc.charAt(j)) {
                    check = true;
                    break;
                }
            }
            if (!check) break;
        }
        return check;
    }

    public static boolean validarSexo(String gender) {
        return "m".equalsIgnoreCase(gender) || gender.equalsIgnoreCase("h");
    }


}

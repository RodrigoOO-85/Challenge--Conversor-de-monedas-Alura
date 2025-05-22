    import java.util.Scanner;

    public class Principal {

        public static void main(String[] args) {
            Scanner entrada = new Scanner(System.in);


            while (true) {
                System.out.println("\nBienvenido/a al sistema de conversión de monedas de Exchangerate-api en Java.\n\n");
                System.out.println("Ingrese la moneda que quiera usar escribiendo el número correspondiente\n");
                System.out.println("1.- Peso Chileno    ===> Boliviano.\n2.- Peso Argentino  ===> Real Brasileño.\n3.- Peso Colombiano ===> Dólar Estadounidense.\n0.- Cerrar Programa.");

                int opcion = -1;


                try{
                    opcion = entrada.nextInt();
                    entrada.nextLine();
                }catch (Exception e){
                    System.out.println("Opción invalida, debe de igresar un número que esta en la lista de monedas a convertir.");
                    entrada.nextLine();
                    continue;
                }

                if(opcion == 0){
                    System.out.println("Programa cerrado, gracias por usar el conversor de Exchangerate-api en java.");
                    System.exit(0);
                }

                if(opcion <1 || opcion > 3){
                    System.out.println("Opción invalida, debe de igresar un número que esta en la lista de monedas a convertir.");
                    continue;
                }

                String moneda1 = "";
                String moneda2 = "";
                boolean opcionValida = true;

                switch (opcion) {
                    case 1 -> {
                        moneda1 = "CLP";
                        moneda2 = "BOB";
                    }
                    case 2 -> {
                        moneda1 = "ARS";
                        moneda2 = "BRL";
                    }
                    case 3 -> {
                        moneda1 = "COP";
                        moneda2 = "USD";
                    }
                }


                System.out.println("Ingrese la cantidad a convertir:");
                double cantidad = 0;

                try {
                    cantidad = entrada.nextDouble();
                    entrada.nextLine();
                    if (cantidad <= 0){
                        System.out.println("La cantidad de dinero a convertir debe ser mayor a cero");
                        continue;
                    }
                }catch (Exception e){
                    System.out.println("Error al ingresar cantidad, inserte un número valido");
                    entrada.nextLine();
                    continue;
                }

                ConexionAapiYcalculo conexion = new ConexionAapiYcalculo(moneda1, moneda2, cantidad);
                DatosExchangeApi resultado = conexion.conexion();

                if (resultado != null) {
                    System.out.println("El valor de " + cantidad + " " + moneda1 + " corresponde según la tasa de conversión de " + resultado.conversion_rate() + " a " + resultado.conversion_result() + " " + moneda2 + " .");

                } else {
                    System.out.println("Error en la obtención de datos de conversión");
                }
            }

        }

    }

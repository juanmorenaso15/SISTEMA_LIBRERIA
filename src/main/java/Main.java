import java.util.Scanner;

import model.Autores;
import model.Libros;
import model.Socios;
import model.Prestamos;
import repositorio.AutoresRepository;
import repositorio.LibrosRepository;
import repositorio.PrestamosRepository;
import repositorio.SociosRepository;


public class Main {
   public static void main(String[] args) {

      Scanner scanner = new Scanner(System.in);
      // //ingresar un nuevo Autor
      AutoresRepository autoresRepository = new AutoresRepository();
      LibrosRepository librosRepository = new LibrosRepository();
      SociosRepository sociosRepository = new SociosRepository();
      PrestamosRepository prestamosRepository = new PrestamosRepository();

      //este opcion es para poder ingresar al menu del sistema
      int opcion;

      do {
         System.out.println("\nSistema de Gestión de Biblioteca");
         System.out.println("1. Gestión de Autores");
         System.out.println("2. Gestión de Libros");
         System.out.println("3. Gestion de socios");
         System.out.println("4. Gestión de Préstamos");
         System.out.println("5. Salir");
         System.out.print("Seleccione una opción: ");

         opcion = scanner.nextInt();
         scanner.nextLine();

         switch (opcion) {
            //si la persona elige la opcion 1 ingresara al sistema de AUTORES:
            case 1:
               int opcionAutor;

               do {
                  System.out.println("\nGestion de Autores: ");
                  System.out.println("1. Insertar nuevo autor");
                  System.out.println("2. Listar todos los autores");
                  System.out.println("3. Buscar autor por nombre");
                  System.out.println("4. Actualizar autor");
                  System.out.println("5. Eliminar Autor");
                  System.out.println("6. Volver al menú principal");
                  System.out.print("Seleccione una opción: ");

                  opcionAutor = scanner.nextInt();
                  scanner.nextLine();

                  switch (opcionAutor) {

                     case 1:
                        //----------------------Agregar autor-----------------------------
                        System.out.print("Ingrese el nombre del Autor: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese la nacionalidad del autor: " + nombre + ": ");
                        String nacionalidad = scanner.nextLine();
                        scanner.nextLine();


                        Autores autores = new Autores(nombre, nacionalidad);

                        autoresRepository.insertarAutor(autores);
                        break;
                     case 2:
                        //------------------listar autores------------------------
                        System.out.println("Lista de autores:");
                        for (Autores autores2: autoresRepository.ListarAutores()) {
                           System.out.println("ID: " + autores2.getId() +
                              "- Nombre: " + autores2.getNombre() +
                              "- nacionalidad: " + autores2.getNacionalidad());
                        }
                        break;
                     case 3:
                        //----------------filtrar por nombre-------------------------------------
                        System.out.print("\nIngrese el nombre a buscar: ");
                        String nombreBuscar = scanner.nextLine();

                        System.out.println("\nResultados de la búsqueda para: " + nombreBuscar);
                        boolean encontrado = false;

                        for (Autores autores2: autoresRepository.ListarAutores()) {
                           if (autores2.getNombre().equalsIgnoreCase(nombreBuscar)) {
                              System.out.println("ID: " + autores2.getId() + "\n" +
                                 "Nombre: " + autores2.getNombre() + "\n" +
                                 "nacionalidad: " + autores2.getNacionalidad() + "\n");
                              encontrado = true;
                           }
                        }

                        if (!encontrado) {
                           System.out.println("No se encontraron autores con el nombre: " + nombreBuscar);
                        }
                        break;
                     case 4:
                        //----------------------------Actualizar datos------------------------------------------------

                        System.out.println("Actualiza tus datos: ");
                        System.out.println("Lista de autores:");
                        for (Autores autores2: autoresRepository.ListarAutores()) {
                           System.out.println("ID: " + autores2.getId() +
                              "- Nombre: " + autores2.getNombre() +
                              "- nacionalidad: " + autores2.getNacionalidad());
                        }
                        System.out.println("Ingrese el ID del autor que quieras actualizar: ");
                        Long id = scanner.nextLong();
                        scanner.nextLine();

                        System.out.println("Ingrese el nuevo nombre: ");
                        String NuevoNombre = scanner.nextLine();

                        System.out.println("Ingrese la nacionalidad: ");
                        String NuevaNacionalida = scanner.nextLine();

                        Autores autoresActualizado = new Autores(NuevoNombre, NuevaNacionalida);
                        autoresActualizado.setId(id);

                        autoresRepository.ActualizarAutores(autoresActualizado);

                        System.out.println("Lista Actualizada de autores: ");

                        for (Autores autores2: autoresRepository.ListarAutores()) {
                           System.out.println("ID: " + autores2.getId() +
                              " - Nombre: " + autores2.getNombre() +
                              " - nacionalidad: " + autores2.getNacionalidad());
                        }
                        break;
                     case 5:
                        //------------------------eliminar-------------------------------------------------------
                        System.out.println("\nEliminar autor");
                        autoresRepository.ListarAutores();

                        System.out.print("ID del autor a eliminar: ");
                        Long ideliminar = scanner.nextLong();
                        scanner.nextLine();

                        System.out.print("¿Eliminar? (si/no): ");
                        String confirmar = scanner.nextLine();

                        if (confirmar.equalsIgnoreCase("si")) {
                           autoresRepository.eliminarAutor(ideliminar);
                           System.out.println("Autor eliminado.");
                           autoresRepository.ListarAutores();
                        } else {
                           System.out.println("Cancelado.");
                        }
                        break;
                     case 6:
                        System.out.println("Volviendo al menú principal...");
                        break;
                     default:
                        System.out.println("Opción no válida. Intente nuevamente.");

                  }

               } while (opcionAutor != 6);
               break;

            case 2:

               int opcionLibro;

               do {
                  System.out.println("\nGestion de Libros");
                  System.out.println("1. Añadir nuevo libro");
                  System.out.println("2. Buscar libro por ISBN o título");
                  System.out.println("3. Listar todos los libros con disponibilidad");
                  System.out.println("4. Modificar datos de un libro");
                  System.out.println("5. Eliminar libro");
                  System.out.println("6. Volver al menú principal");
                  System.out.print("Seleccione una opción: ");

                  opcionLibro = scanner.nextInt();
                  scanner.nextLine();

                  switch (opcionLibro) {
                     case 1:
                        //----------------------Agregar libro-----------------------------

                        System.out.println("\nAñade un libro: ");

                        System.out.print("ISBN del libro: ");
                        String isbn = scanner.nextLine();

                        System.out.print("Título del libro: ");
                        String titulo = scanner.nextLine();

                        System.out.println("Lista de autores:");
                        for (Autores autores2: autoresRepository.ListarAutores()) {
                           System.out.println("ID: " + autores2.getId() +
                              "- Nombre: " + autores2.getNombre() +
                              "- nacionalidad: " + autores2.getNacionalidad());
                        }

                        System.out.print("ID del autor: ");
                        Long autorId = scanner.nextLong();
                        scanner.nextLine();

                        System.out.print("Año de publicación: ");
                        int año = scanner.nextInt();

                        System.out.print("Cantidad total: ");
                        int total = scanner.nextInt();

                        System.out.print("Cantidad disponible: ");
                        int disponible = scanner.nextInt();
                        scanner.nextLine();

                        Libros libro = new Libros(isbn, titulo, autorId, año, total, disponible);
                        librosRepository.insertarLibros(libro);
                        System.out.println("Libro añadido exitosamente.");

                        break;
                     case 2:
                        //----------------filtrar por isbn y nombre-------------------------------------
                        int buscarporisbnotitulo;

                        do {
                           System.out.println("\nBuscar libro por ISBN o título");
                           System.out.println("1. Busar por isbn");
                           System.out.println("2. Buscar por titulo");
                           System.out.println("3. Deseas voler");
                           System.out.print("Selecion una opcion: ");

                           buscarporisbnotitulo = scanner.nextInt();
                           scanner.nextLine();

                           switch (buscarporisbnotitulo) {
                              case 1:
                                 System.out.println("Ingrese el isbn del libro: ");
                                 String isbnBuscar = scanner.nextLine();
                                 System.out.println("Resultados de la busqueda para: " + isbnBuscar);
                                 boolean encontrado = false;

                                 for (Libros libros: librosRepository.ListarLibros()) {
                                    if (libros.getIsbn().equalsIgnoreCase(isbnBuscar)) {
                                       System.out.println("ISBN: " + libros.getIsbn() + "\n" + "\n" +
                                          "Titulo: " + libros.getTitulo() + "\n" + "\n" +
                                          "Autor ID: " + libros.getAutor_id() + "\n" + "\n" +
                                          "Año Publicacion: " + libros.getAño_publicacion() + "\n" + "\n" +
                                          "Cantidad Total: " + libros.getCantidad_total() + "\n" + "\n" +
                                          "Cantidad Disponible: " + libros.getCantidad_disponible() + "\n");
                                       encontrado = true;
                                    }
                                 }

                                 if (!encontrado) {
                                    System.out.println("No se encontraron libros con el isbn: " + isbnBuscar);
                                 }
                                 break;
                              case 2:
                                 System.out.println("Ingrese el tiutlo del libro: ");
                                 String tituloBuscar = scanner.nextLine();
                                 System.out.println("Resultados de la busqueda para: " + tituloBuscar + "\n");
                                 boolean encontradotitulo = false;

                                 for (Libros libros: librosRepository.ListarLibros()) {
                                    if (libros.getTitulo().equalsIgnoreCase(tituloBuscar)) {
                                       System.out.println("ISBN: " + libros.getIsbn() + "\n" + "\n" +
                                          "Titulo: " + libros.getTitulo() + "\n" + "\n" +
                                          "Autor ID: " + libros.getAutor_id() + "\n" + "\n" +
                                          "Año Publicacion: " + libros.getAño_publicacion() + "\n" + "\n" +
                                          "Cantidad Total: " + libros.getCantidad_total() + "\n" + "\n" +
                                          "Cantidad Disponible: " + libros.getCantidad_disponible() + "\n");
                                       encontradotitulo = true;
                                    }
                                 }

                                 if (!encontradotitulo) {
                                    System.out.println("No se encontraron libros con el isbn: " + tituloBuscar);
                                 }
                                 break;
                              case 3:
                                 System.out.println("Volviendo al menú principal...");

                                 break;
                              default:
                                 break;
                           }
                        } while (buscarporisbnotitulo != 3);
                        break;
                     case 3:
                        //------------------listar libros con disponibilidad------------------------

                        System.out.println("Lista de libros: ");
                        for (Libros libros: librosRepository.ListarLibros()) {
                           librosRepository.mostrarLibro(libros);
                        }

                        break;
                     case 4:

                        //----------------------------Actualizar datos------------------------------------------------

                        System.out.println("Actualiza los dato de un libro: ");
                        System.out.println("Lista de libros: ");
                        for (Libros libros: librosRepository.ListarLibros()) {
                           librosRepository.mostrarLibro(libros);
                        }

                        System.out.println("Ingrese el isbn del libro: ");
                        String isbnBuscar = scanner.nextLine();
                        Libros libroEncontrado = librosRepository.obtenerLibroPorIsbn(isbnBuscar);


                        int opcionEditar;

                        do {
                           System.out.println("Que deseas editar");
                           System.out.println("1. Titulo");
                           System.out.println("2. Autor del libro");
                           System.out.println("3. Años de publicacion");
                           System.out.println("4. cantidad total");
                           System.out.println("5. Cantidad disponible");
                           System.out.println("6. Editar el isbn");
                           System.out.println("7. subir cambios y salir");
                           System.out.println("Elige una opcion: ");
                           opcionEditar = scanner.nextInt();
                           scanner.nextLine();

                           switch (opcionEditar) {
                              case 1:
                                 System.out.println("Ingresa el nuevo titulo: ");
                                 String nuevoTitulo = scanner.nextLine();
                                 libroEncontrado.setTitulo(nuevoTitulo);
                                 break;
                              case 2:
                                 System.out.println("Ingresa el nuevo autor ID: ");
                                 Long nuevoAutorId = scanner.nextLong();
                                 scanner.nextLine();
                                 libroEncontrado.setAutor_id(nuevoAutorId);
                                 break;
                              case 3:
                                 System.out.println("Ingresa el nuevo año de publicacion: ");
                                 int nuevoAño = scanner.nextInt();
                                 scanner.nextLine();
                                 libroEncontrado.setAño_publicacion(nuevoAño);
                                 break;
                              case 4:
                                 System.out.println("Ingresa la nueva cantidad total: ");
                                 int nuevaCantidadTotal = scanner.nextInt();
                                 scanner.nextLine();
                                 libroEncontrado.setCantidad_total(nuevaCantidadTotal);
                                 break;
                              case 5:
                                 System.out.println("Ingresa la nueva cantidad disponible: ");
                                 int nuevaCantidadDisponible = scanner.nextInt();
                                 scanner.nextLine();
                                 libroEncontrado.setCantidad_disponible(nuevaCantidadDisponible);
                                 break;
                              case 6:
                                 System.out.println("Ingresa el nuevo ISBN: ");
                                 String nuevoIsbn = scanner.nextLine();
                                 libroEncontrado.setIsbn(nuevoIsbn);
                                 break;
                              case 7:
                                 librosRepository.ActualizarLibros(libroEncontrado);
                                 System.out.println("Cambios guardados exitosamente.");

                                 break;
                              default:
                                 break;
                           }

                        } while (opcionEditar != 7);

                        break;
                     case 5:

                        //------------------------eliminar-------------------------------------------------------

                        System.out.println("Eliminar Libro: ");

                        System.out.println("Lista de libros: ");
                        for (Libros libros: librosRepository.ListarLibros()) {
                           librosRepository.mostrarLibro(libros);
                        }

                        System.out.println("ISBN del libro que deseas eliminar: ");
                        String isbnELiminar = scanner.nextLine();


                        System.out.print("¿Eliminar? (si/no): ");
                        String confirmar = scanner.nextLine();

                        if (confirmar.equalsIgnoreCase("si")) {
                           librosRepository.eliminarLibro(isbnELiminar);
                           System.out.println("Libro eliminado. ");
                           librosRepository.ListarLibros();
                        } else {
                           System.out.println("Cancelado.");
                        }

                        break;
                     case 6:
                        System.out.println("Volviendo al menú principal...");
                        break;

                     default:
                        break;
                  }

               } while (opcionLibro != 6);

               break;
            case 3:

               int opcionSocio;

               do {
                  System.out.println("\nGestion de Socio");
                  System.out.println("1. Añadir nuevo Socio");
                  System.out.println("2. Buscar socio por DNI");
                  System.out.println("3. Listar todos los socios");
                  System.out.println("4. Modificar datos de un socio");
                  System.out.println("5. Eliminar socio");
                  System.out.println("6. Volver al menú principal");
                  System.out.print("Seleccione una opción: ");

                  opcionSocio = scanner.nextInt();
                  scanner.nextLine();

                  switch (opcionSocio) {
                     case 1:

                        //----------------------Agregar Socio-----------------------------

                        System.out.println("Ingrese el nombre del socio: ");
                        String nombreSocio = scanner.nextLine();
                        System.out.println("Ingrese el apellido del socio: ");
                        String apellidoSocio = scanner.nextLine();
                        System.out.println("Ingrese el DNI del socio: ");
                        String dniSocio = scanner.nextLine();
                        System.out.println("Ingrese el teléfono del socio: ");
                        String telefonoSocio = scanner.nextLine();

                        Socios socio = new Socios(nombreSocio, apellidoSocio, dniSocio, telefonoSocio);


                        sociosRepository.insertarSocio(socio);

                        break;
                     case 2:

                        //----------------filtrar por DNI-------------------------------------
                        System.out.print("\nIngrese el DNI a buscar: ");
                        String dniBuscar = scanner.nextLine();
                        System.out.println("\nResultados de la búsqueda para: " + dniBuscar);
                        boolean encontrado = false;

                        for (Socios socio2: sociosRepository.ListarSocios()) {
                           if (socio2.getDNI().equalsIgnoreCase(dniBuscar)) {
                              System.out.println("ID: " + socio2.getId() + "\n" +
                                 "Nombre: " + socio2.getNombre() + "\n" +
                                 "Apellido: " + socio2.getApellido() + "\n" +
                                 "DNI: " + socio2.getDNI() + "\n" +
                                 "Teléfono: " + socio2.getTelefono() + "\n");
                              encontrado = true;
                           }
                        }

                        if (!encontrado) {
                           System.out.println("No se encontró ningún socio con el DNI: " + dniBuscar);
                        }

                        break;
                     case 3:

                        //------------------listar socios------------------------

                        System.out.println("Lista de socios: ");
                        for (Socios socio2: sociosRepository.ListarSocios()) {
                           System.out.println("ID: " + socio2.getId() +
                              " - Nombre: " + socio2.getNombre() +
                              " - Apellido: " + socio2.getApellido() +
                              " - DNI: " + socio2.getDNI() +
                              " - Teléfono: " + socio2.getTelefono());
                        }

                        break;
                     case 4:

                        //----------------------------Actualizar datos------------------------------------------------

                        System.out.println("Actualiza los dato de un socio: ");
                        System.out.println("Lista de socios: ");
                        for (Socios socio2: sociosRepository.ListarSocios()) {
                           System.out.println("ID: " + socio2.getId() +
                              " - Nombre: " + socio2.getNombre() +
                              " - Apellido: " + socio2.getApellido() +
                              " - DNI: " + socio2.getDNI() +
                              " - Teléfono: " + socio2.getTelefono());
                        }
                        System.out.println("Ingrese el ID del socio: ");
                        int idSocio = scanner.nextInt();
                        Socios socioEncontrado = sociosRepository.obtenerSocioPorId(idSocio);
                        scanner.nextLine();

                        int opcionEditarSocio;

                        do {
                           System.out.println("Que deseas editar");
                           System.out.println("1. Nombre");
                           System.out.println("2. Apellido");
                           System.out.println("3. DNI");
                           System.out.println("4. Teléfono");
                           System.out.println("5. Subir cambios y salir");
                           System.out.println("Elige una opcion: ");
                           opcionEditarSocio = scanner.nextInt();
                           scanner.nextLine();

                           switch (opcionEditarSocio) {
                              case 1:
                                 System.out.println("Ingresa el nuevo nombre: ");
                                 String nuevoNombreSocio = scanner.nextLine();
                                 socioEncontrado.setNombre(nuevoNombreSocio);
                                 break;
                              case 2:
                                 System.out.println("Ingresa el nuevo apellido: ");
                                 String nuevoApellidoSocio = scanner.nextLine();
                                 socioEncontrado.setApellido(nuevoApellidoSocio);
                                 break;
                              case 3:
                                 System.out.println("Ingresa el nuevo DNI: ");
                                 String nuevoDniSocio = scanner.nextLine();
                                 socioEncontrado.setDNI(nuevoDniSocio);
                                 break;
                              case 4:
                                 System.out.println("Ingresa el nuevo teléfono: ");
                                 String nuevoTelefonoSocio = scanner.nextLine();
                                 socioEncontrado.setTelefono(nuevoTelefonoSocio);
                                 break;
                              case 5:
                                 sociosRepository.ActualizarSocios(socioEncontrado);
                                 System.out.println("Cambios guardados exitosamente.");

                                 break;
                              default:
                                 System.out.println("Opción inválida");
                                 break;
                           }

                        } while (opcionEditarSocio != 5);

                        break;
                     case 5:


                        //------------------------eliminar solo si no tiene préstamos pendientes-------------------------------------------------------                        
                        System.out.println("Eliminar Socio: ");
                        System.out.println("Lista de socios: ");
                        for (Socios socio2: sociosRepository.ListarSocios()) {
                           System.out.println("ID: " + socio2.getId() +
                              " - Nombre: " + socio2.getNombre() +
                              " - Apellido: " + socio2.getApellido() +
                              " - DNI: " + socio2.getDNI() +
                              " - Teléfono: " + socio2.getTelefono());
                        }

                        System.out.println("ID del socio que deseas eliminar: ");
                        int idEliminarSocio = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("¿Eliminar? (si/no): ");
                        String confirmarEliminarSocio = scanner.nextLine();
                        if (confirmarEliminarSocio.equalsIgnoreCase("si")) {
                           sociosRepository.eliminarSocio(idEliminarSocio);
                           System.out.println("Socio eliminado. ");
                           sociosRepository.ListarSocios();
                        } else {
                           System.out.println("Cancelado.");
                        }

                        break;
                     case 6:

                        System.out.println("Volviendo al menú principal...");

                        break;

                     default:
                        System.out.println("Opción no válida. Intente nuevamente.");

                  }
               } while (opcionSocio != 6);
               break;

            case 4:
               int opcionPrestamo;
               do {
                  System.out.println("\nGestión de Préstamos");
                  System.out.println("1. Realizar préstamo de un libro");
                  System.out.println("2. Devolver un libro");
                  System.out.println("3. Listar préstamos activos");
                  System.out.println("4. Listar préstamos de un socio específico");
                  System.out.println("5. Ver libros prestados actualmente y quién los tiene");
                  System.out.println("6. Volver al menú principal");
                  System.out.print("Seleccione una opción: ");

                  opcionPrestamo = scanner.nextInt();
                  scanner.nextLine();

                  switch (opcionPrestamo) {
                     case 1:
                        // -----------------Realizar préstamo-------------------------------
                        System.out.println("Libros disponibles para préstamo:");
                        for (Libros l: librosRepository.ListarLibros()) {
                           if (l.getCantidad_disponible() > 0) {
                              librosRepository.mostrarLibro(l);
                           }
                        }

                        System.out.print("Ingrese el ISBN del libro a prestar: ");
                        String isbnPrestar = scanner.nextLine();
                        Libros libroPrestar = librosRepository.obtenerLibroPorIsbn(isbnPrestar);

                        if (libroPrestar == null) {
                           System.out.println("No se encontró el libro con ISBN: " + isbnPrestar);
                           break;
                        }

                        if (libroPrestar.getCantidad_disponible() <= 0) {
                           System.out.println("El libro no tiene unidades disponibles.");
                           break;
                        }
                        System.out.println("Lista de socios: ");
                        for (Socios socio2: sociosRepository.ListarSocios()) {
                           System.out.println("ID: " + socio2.getId() +
                              " - Nombre: " + socio2.getNombre() +
                              " - Apellido: " + socio2.getApellido() +
                              " - DNI: " + socio2.getDNI() +
                              " - Teléfono: " + socio2.getTelefono());
                        }

                        System.out.print("Ingrese el ID del socio: ");
                        int idSocioPrestamo = scanner.nextInt();
                        scanner.nextLine();

                        Socios socioParaPrestamo = sociosRepository.obtenerSocioPorId(idSocioPrestamo);
                        if (socioParaPrestamo == null) {
                           System.out.println("No existe un socio con ID: " + idSocioPrestamo);
                           break;
                        }

                        java.sql.Date fechaAhora = new java.sql.Date(System.currentTimeMillis());
                        java.sql.Date fechaPrevista = new java.sql.Date(fechaAhora.getTime() + 14L * 24 * 60 * 60 * 1000); // 14 días

                        Prestamos nuevoPrestamo = new Prestamos(isbnPrestar, idSocioPrestamo, fechaAhora, fechaPrevista, null, Prestamos.EstadoPrestamo.PRESTADO);
                        prestamosRepository.realizarPrestamo(nuevoPrestamo);

                        libroPrestar.setCantidad_disponible(libroPrestar.getCantidad_disponible() - 1);
                        librosRepository.ActualizarLibros(libroPrestar);

                        System.out.println("Préstamo registrado correctamente.");
                        break;

                     case 2:
                        //------------------------------------ Devolver libro---------------------------------------------
                        java.util.List < Prestamos > activos = prestamosRepository.listarPrestamosActivos();
                        if (activos.isEmpty()) {
                           System.out.println("No hay préstamos activos.");
                           break;
                        }

                        System.out.println("Préstamos activos:");
                        for (Prestamos prestamos: activos) {
                           prestamosRepository.mostrarPrestamo(prestamos);
                        }

                        System.out.print("Ingrese el ID del préstamo a devolver: ");
                        long idPrestamoDevolver = scanner.nextLong();
                        scanner.nextLine();

                        Prestamos seleccionado = null;
                        for (Prestamos prestamos: activos) {
                           if (prestamos.getId() != null && prestamos.getId() == idPrestamoDevolver) {
                              seleccionado = prestamos;
                              break;
                           }
                        }

                        if (seleccionado == null) {
                           System.out.println("No se encontró ese préstamo activo.");
                           break;
                        }

                        java.sql.Date fechaDevolucion = new java.sql.Date(System.currentTimeMillis());
                        prestamosRepository.devolverPrestamo(idPrestamoDevolver, fechaDevolucion);

                        String isbnDev = seleccionado.getLibro_isbn();
                        Libros libroDev = librosRepository.obtenerLibroPorIsbn(isbnDev);
                        if (libroDev != null) {
                           libroDev.setCantidad_disponible(libroDev.getCantidad_disponible() + 1);
                           librosRepository.ActualizarLibros(libroDev);
                        }

                        System.out.println("Préstamo devuelto correctamente.");
                        break;

                     case 3:
                        //----------------------------------- Listar préstamos activos------------------------------------------------------
                        System.out.println("Préstamos activos:");
                        for (Prestamos prestamos: prestamosRepository.listarPrestamosActivos()) {
                           prestamosRepository.mostrarPrestamo(prestamos);
                        }
                        break;

                     case 4:
                        //---------------------------------------- Listar préstamos de un socio específico--------------------------------------------------
                        System.out.println("Lista de socios: ");
                        for (Socios socio2: sociosRepository.ListarSocios()) {
                           System.out.println("ID: " + socio2.getId() +
                              " - Nombre: " + socio2.getNombre() +
                              " - Apellido: " + socio2.getApellido() +
                              " - DNI: " + socio2.getDNI() +
                              " - Teléfono: " + socio2.getTelefono());
                        }
                        System.out.print("Ingrese el ID del socio: ");
                        int idBuscar = scanner.nextInt();
                        scanner.nextLine();
                        java.util.List < Prestamos > prestamosSocio = prestamosRepository.listarPrestamosPorSocio(idBuscar);
                        if (prestamosSocio.isEmpty()) {
                           System.out.println("No se encontraron préstamos para el socio con ID: " + idBuscar);
                        } else {
                           for (Prestamos prestamos: prestamosSocio) {
                              prestamosRepository.mostrarPrestamo(prestamos);
                           }
                        }
                        break;

                     case 5:
                        //-------------------------------------- Ver libros prestados actualmente y quién los tiene-----------------------------------------
                        System.out.println("Libros prestados actualmente:");
                        for (Prestamos prestamos: prestamosRepository.listarPrestamosActivos()) {
                           Libros libros = librosRepository.obtenerLibroPorIsbn(prestamos.getLibro_isbn());
                           Socios s = sociosRepository.obtenerSocioPorId(prestamos.getSocio_id());
                           String titulo = libros != null ? libros.getTitulo() : "(Título no disponible)";
                           String socioNombre = s != null ? s.getNombre() + " " + s.getApellido() : "(Socio no disponible)";
                           System.out.println("ISBN: " + prestamos.getLibro_isbn() + " - Título: " + titulo + " - Prestado a: " + socioNombre + " - Fecha préstamo: " + prestamos.getFecha_prestamo());
                        }
                        break;

                     case 6:
                        System.out.println("Volviendo al menú principal...");
                        break;

                     default:
                        System.out.println("Opción inválida. Intente nuevamente.");

                  }

               } while (opcionPrestamo != 6);
               break;

            case 5:
               System.out.println(
                  "Hasta luego :)");
               break;
            default:
               System.out.println("Opcion invalida intente otra vez");
               break;
         }



      }
      while (opcion != 5);

      scanner.close();

   }




}
# Change Log
All notable changes to this project will be documented in this file.

## [Ver_Alpha_1.1] - 2025/02/05
WE ARE ANOUNCING THE END OF THE PRE-ALPHA **(YAY, WE ACTUALLY MANAGED TO DO IT!!!)**

We are officially one step closer to the launch of the app, but we have still work to do in order to bring the best experience to our users, so please be patient and rest asured that there will be further improvements in the app.

This version of the app brings more data retrieving related changes, as well as a fully functional login page for each user with their respective trips and events in their main page.
### Added
#### Class "Viajes y Eventos"
- Now you can select one trip from the table "Viajes" in order to show all of its events in the table "Eventos".
- Now the title is displayed with the color of the agency.
- Added buttons to delete either a trip or an event.
- Now you can delete one trip by selecting it in the table and then pressing the buton "Borrar Viaje"
#### Class "Controlador"
- Added new methods for "GestorVuelos" and "GestorAlojamientos" in charge of retrieving all rows of both tables from the DDBB given a certain trip's ID.
- Added the method "getTiposViaje()".
- Added the method "getAerolineaPorCodigo()".
- Added the method "getAeropuertoPorCodigo()".
- Added the method "getCiudadPorId()".
- Added the method "getAeropuertoPorIdCiudad()".
- Added the method "getViajePorId()".
- Added the method "getAgenciaPorId()".
- Added the method "getTipoDormitorioPorCodigo()".
- Added the method "getAlojamientoPorCodigoDormitorio()".
- Added the method "deleteViajePorId()".
#### Class "Gestor TipoViaje"
- Added a method to retrieve all the rows of the table "tipoViaje" from the DDBB.
#### Class "Viajes Elorrieta"
- Now the menu option "Nuevo Evento" is fully functional.
#### Class "Gestor Vuelos"
- Created new class "GestorVuelos".
- Created new method to retrieve all the flights from the DDBB given a certain trip's ID.
#### Class "Gestor Aerolineas"
- Created new class "GestorAerolineas".
- Created new method "getAerolineaPorCodigo()".
#### Class "Gestor Aeropuerto"
- Created new class "GestorAeropuerto".
- Created new method "getAeropuertoPorCodigo()".
- Created new method "getAeropuertoPorIdCiudad()".
#### Class "Gestor Ciudad"
- Created new class "GestorCiudad".
- Created new method "getCiudadPorId()".
#### Class "Gestor Viajes"
- The method "getViajePorId()" has been added.
- The method "deleteViajePorId()" has been added.
#### Class "Gestor Agencia"
- The method "getAgenciaPorId()" has been added.
#### Class "Gestor Alojamiento"
- Created new class "GestorAlojamiento".
- Created new method "getAlojamientosPorIdViaje()".
- Created new method "getAlojamientoPorCodigoDormitorio()".
#### Class "Gestor Tipo Dormitorio"
- Created new class "GestorTipoDormitorio".
- Created new method "getTipoDormitorioPorCodigo()".
#### Class "SQLQuerys"
- The query "SELECT_TODOS_TIPOS_VIAJE_WHERE_DESCRIPCION" has been added.
- The query "SELECT_TODOS_AEROLINEAS_WHERE_CODIGO" has been added.
- The query "SELECT_TODOS_AEROPUERTO_WHERE_CODIGO" has been added.
- The query "SELECT_TODOS_CIUDAD_WHERE_ID" has been added.
- The query "SELECT_TODOS_AEROPUERTO_WHERE_ID" has been added.
- The query "SELECT_TODOS_VIAJES_WHERE_ID" has been added.
- The query "SELECT_TODOS_AGENCIA_WHERE_ID" has been added.
- The query "SELECT_TODOS_ALOJAMIENTO_WHERE_IDVIAJE" has been added.
- The query "SELECT_TODOS_TIPODORMITORIO_WHERE_CODIGO" has been added.
- The query "SELECT_TODOS_ALOJAMIENTO_WHERE_CODIGO_DORMITORIO" has been added.
- The query "DELETE_ALL_VIAJE_WHERE_ID" has been added.
### Changed
#### Class "Viajes y Eventos"
- The header of both tables can no longer be dragged.
- The cells of both tables are no longer editable.
#### Class "Controlador"
- Changed method "mostrarActividades()" name to "getActividades()".
- Renamed incorrectly named method "getViajesId()" to "getViajesPorIdAgencia()".
#### Class "Gestor Actividad"
- Renamed incorrectly named method "getActividades()" to "getActividadesPorIdViaje()".
- Renamed method "mostrarActividades()" to "getActividades()".
- Deleted unused code.
#### DDBB
- Changed the required parameters to create an entry into the table "vuelo".
- Changed the values of some country names to make a link between the tables "pais" and "aerolineas".
#### Class "Vuelo"
- Changed the required parameters to create the object "Vuelo".
#### Class "Gestor Actividad"
- Deleted missplaced method "mostrarPaisViaje()" and unused code.
#### Class "SQLQuerys"
- The query "SELECT_TODOS_PAISES_POR_CODIGO" has been renamed to "SELECT_TODOS_PAISES_WERE_CODIGO"
- The query "SELECT_TODOS_VIAJES_ID" has been renamed to "SELECT_TODOS_VIAJES_WHERE_IDAGENCIA"
### Fixed
#### Class "Gestor TipoViaje"
- Fixed a bug where incorrect values where returned from the DDBB.
#### Class "Nuevo Viaje"
- Fixed a bug where "comboTipoViaje" was being filled with the wrong method.
#### Class "Controlador"
- Fixed the method "getActividadesPorIdViaje()" to work as intended.
#### Class "SQLQuerys"
- Fixed the query "INSERT_NEW_ACTIVIDAD" which was not finished.

## [Ver_PreAlpha_1.0.1] - 2025/02/04
Data retrieving related changes.
### Added
#### Class "Controlador"
- Added the method "getTipoViajeObjetoPorCodigo()".
- Added the method "getPaisPorCodigo()".
#### Class "Gestor TipoViaje"
- Added a method to get the object "TipoAgencia" of a certain agency given its ID.
#### Class "Gestor Viajes"
- Added a method to get all the trips of a certain agency.
#### Class "SQLQuerys"
- Added a query to get the object "TipoViaje" given the value of "codigo".
### Changed
#### Class "Gestor Paises"
- Renamed the method "mostrarPaisViaje()" to "getPaisPorCodigo()".
#### Class "Controlador"
- Unused methods "mostrarPaises()" and "mostrarPaisArray()" turned to comment for now.
### Fixed 
#### Class "SQLQuerys"
- Fixed bad statement of the query "SELECT_TODOS_TIPOS_VIAJE".
#### Class "Viajes y Eventos"
- Now the trips of the agency that logged in are visible inside the table "Viajes"


## [Ver_PreAlpha_1.0] - 2025/02/04
General Update to the app adding new features such as a drop-down menu and a brand new Class to get the trips for each agency.
### Added
#### Class "Viajes Elorrieta"
- Added "Nuevo Evento" drop-down menu with "Nuevo Vuelo", "Nuevo Alojamiento" and "Nueva Actividad" options.
#### Class "Controlador"
 - Method "getInstanceAgencia()" added.
 - Method "setInstanceAgencia()" added.
 - Method "deleteInstanceAgencia()" added.
####  Class "Viajes y Eventos"
- Added the logo and name of the agency that has logged in to the "Viajes y Eventos" panel.
#### Class "Gestor Viajes"
- New class "Gestor Viajes" created.
- Added code to retrieve all trips of an specific agency (In process).
### Changed
#### Class "Viajes Elorrieta"
- Deleted the "setAgenciaLogin()" function.
- Added the code in charge of moving the agency which logged in to the "Viajes y Eventos" panel.
- Frame size changed to 1300 x 900.
- The controller is now instantiated, if there is no instance it creates it.
#### Class "Bienvenida"
 - Now the background image is rescaled to the panel size.
 - Panel size changed to 1300 x 900.
 - The controller is now instantiated, if there is no instance it creates it.
#### Class "Formulario Login"
- You can now log in by pressing enter in the password field.
- Now the background image is rescaled to the panel size. 
- Panel size changed to 1300 x 900.
- The controller is now instantiated, if there is no instance it creates it.
#### Class "Viajes y Eventos"
 - Added Agency object at the beginning. 
 - Panel size changed to 1300 x 900.
 - The controller is now instantiated, if there is no instance it creates it.
 - Fixed the layout of the panel.
 - Replaced the "Nuevo viaje", "Nuevo Evento" and "Desconectar" buttons with the dropdown menu.
#### Class "Gestor Agencia"
-  Unnecesary code deleted
### Fixed
#### Class "Viajes Elorrieta"
 - Fixed the construction of the "Viajes y Eventos" panel, through which the data of the agency with which you logged in could not be passed.
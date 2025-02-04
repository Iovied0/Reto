# Change Log
All notable changes to this project will be documented in this file.

## [Ver_PreAlpha_1.0.1] - 2025/02/04
---
Data retrieving related changes

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
---
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
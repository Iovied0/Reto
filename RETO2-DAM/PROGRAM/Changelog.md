# Change Log
All notable changes to this project will be documented in this file.

## [Ver_PreAlpha_1.0] - 2025/02/04
---
General Update to the app adding new features such as a drop-down menu and a brand new Class to get the trips for each agency.
### Added
#### Class "Viajes Elorrieta"
- Added "Nuevo Evento" drop-down menu with "Nuevo Vuelo", "Nuevo Alojamiento" and "Nueva Actividad" options.
#### Class "Controlador"
 - Function "getInstanceAgencia()" added.
 - Function "setInstanceAgencia()" added.
 - Function "deleteInstanceAgencia()" added.
####  Class "Viajes y Eventos"
- Added the logo and name of the agency that has logged in to the "Travel and Events" panel.
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
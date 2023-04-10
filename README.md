# SunCalc - Sunrise and Sunset Calculation

SunCalc is a simple Java project that calculates the sunrise and sunset times based on geographic location and date. The project uses astronomical formulas to accurately calculate the sunrise and sunset times.

References:
  - [General Solar Position Calculations - NOAA](https://gml.noaa.gov/grad/solcalc/solareqns.PDF)
  

## Requirements

- Java Development Kit (JDK) 8 or higher

## Project Structure

The project is organized into the following classes:

1. `SunCalc`: Main class that performs the sunrise and sunset calculations.
2. `Location`: Class representing the geographic location with latitude, longitude, and time zone (UTC).
3. `TimeUtils`: Utility class containing time conversion methods.
4. `SunriseAndSunset`: Class containing the `main` method to run the application with examples.

## Usage

To use the project, follow these steps:

1. Compile and run the `SunriseAndSunset` class in your favorite Java IDE.
2. Configure the location for which you want to calculate the sunrise and sunset times in the `SunriseAndSunset` class. Set the latitude, longitude, and UTC values in the `Location` object.
3. Run the application, and the sunrise and sunset times will be printed to the console.

## Example

Here's an example of how to set up a `Location` object and use the `SunCalc` class:

```java
Location location = new Location();
location.setLatitude(-33.2394434);
location.setLongitude(-60.318236);
location.setUtc(-3);

LocalDate day = LocalDate.now();
SunCalc sunCalculator = new SunCalc(day);

LocalTime sunrise = sunCalculator.calculateSunriseHour(location);
LocalTime sunset = sunCalculator.calculateSunsetHour(location);

System.out.println("Sunrise - " + sunrise);
System.out.println("Sunset - " + sunset);
```

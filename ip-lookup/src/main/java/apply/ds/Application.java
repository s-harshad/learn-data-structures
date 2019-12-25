package apply.ds;

import coding.trie.SimpleTrieST;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Stream;

/**
 * Read CSV file "resources/ip-to-country.csv"; parse and insert data into trie symbol table for
 * lookup
 *
 * @author Harshad Shrishrimal
 */
public class Application {

  // countryCode -> countryModel
  // using a map so that we don't create same CountryModel Objects again and again
  private Map<String, CountryModel> countryMapByCountryCode = new HashMap<>(60);

  // our trie data-structure
  private final SimpleTrieST<CountryModel> trie = new SimpleTrieST<>();

  // this object is returned to user, when data is not found in trie
  private static final CountryModel NOT_FOUND = new CountryModel("NA", "NOT FOUND");

  private static final String DOT = ".";
  private static final String fileName = "ip-to-country.csv";

  public static void main(String[] args) throws IOException, URISyntaxException {

    Application application = new Application();
    URL resource = application.getClass().getClassLoader().getResource(fileName);

    // read each line and insert data into trie
    try (Stream<String> lines = Files.lines(Path.of(resource.toURI()))) {
      lines.forEach(
          line -> {
            application.insertDataIntoTrie(line);
          });
    }

    // clear the map as we don't need it any more
    application.countryMapByCountryCode = null;

    // read input from user and print country info if it exists
    try (Scanner userInput = new Scanner(System.in)) {
      while (true) {
        System.out.print("Ready for a new ip-address (^d to exit) : ");
        String input = userInput.nextLine();

        if (!input.isEmpty()) {
          // since we are only storing the 1st 3 parts of ip
          // remove the last from input
          String ip = first3PartsOfIPOnly(input);
          CountryModel result = application.trie.get(ip);
          result = result == null ? NOT_FOUND : result;
          System.out.println(result);
        }
      }
    }
  }

  /**
   * Parse the given line, and insert data in the necessary collections.
   *
   * @param line a line from csv file
   */
  private void insertDataIntoTrie(String line) {

    // split csv line, format and create the necessary object.
    String[] data = line.split(",");
    data[0] = data[0].replaceAll("\"", "");
    data[2] = data[2].replaceAll("\"", "");
    data[3] = data[3].replaceAll("\"", "");
    if (5 == data.length) {
      data[4] = data[4].replaceAll("\"", "");
      data[3] = data[3] + "," + data[4];
      data[4] = null;
    }

    // place the county details on map; if not already present.
    countryMapByCountryCode.putIfAbsent(data[2], new CountryModel(data[2], data[3]));

    // convert decimal ip to actual ip and insert into trie.
    // we are only inserting the 1st 3 parts.
    String ip = first3PartsOfIPOnly(longToIp(data[0]));
    trie.put(ip, countryMapByCountryCode.get(data[2]));
  }

  /**
   * Given ip-address in decimal format, convert to ip-v4 format
   *
   * @param ipDecimalFormat ip-address decimal format
   * @return ip-address in format x.x.x.x
   */
  private static String longToIp(String ipDecimalFormat) {
    Long ip = Long.valueOf(ipDecimalFormat);

    StringBuilder sb = new StringBuilder(15);
    for (int i = 0; i < 4; i++) {
      sb.insert(0, Long.toString(ip & 0xFF));
      if (i < 3) {
        sb.insert(0, DOT);
      }
      ip = ip >> 8;
    }
    return sb.toString();
  }

  /**
   * Given ip-address in format x.y.z.a return x.y.z
   *
   * @param ip ip-address format x.y.z.a
   * @return ip-adress removing the last information
   */
  private static String first3PartsOfIPOnly(String ip) {
    String[] ipParts = ip.split("\\.");
    return ipParts[0] + "." + ipParts[1] + "." + ipParts[2];
  }
}

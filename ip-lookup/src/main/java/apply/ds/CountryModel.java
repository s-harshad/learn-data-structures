package apply.ds;

import java.util.Objects;

/**
 * Model Object to store Country Information
 *
 * @author Harshad Shrishrimal
 */
final class CountryModel {

  private final String countryCode; // 2 character country code
  private final String countryName; // country name

  public CountryModel(String countryCode, String countryName) {
    this.countryCode = countryCode;
    this.countryName = countryName;
  }

  public String getCountryName() {
    return countryName;
  }

  public String getCountryCode() {
    return countryCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CountryModel that = (CountryModel) o;
    return Objects.equals(countryCode, that.countryCode)
        && Objects.equals(countryName, that.countryName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(countryCode, countryName);
  }

  @Override
  public String toString() {
    return "CountryModel{"
        + "countryCode='"
        + countryCode
        + '\''
        + ", countryName='"
        + countryName
        + '\''
        + '}';
  }
}

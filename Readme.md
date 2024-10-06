## a. What did the system do?

The application is a backend system designed to fetch and compare vehicle part number data from multiple sources. Its primary functions include:

1. **Data Retrieval**: Connects to various data sources (internal systems) to fetch vehicle part numbers and related information.
2. **Data Comparison**: Compares part numbers and associated details across different systems to identify mismatches or inconsistencies using rules provided.
3. **Mismatch Reporting**: Generates result highlighting discrepancies between different data sources.
4. **API Integration**: Provides APIs for frontend applications to query the mismatch data and generate reports.

## b. What other systems have you seen in the wild like that?

Similar systems exist in various industries where data consistency across multiple sources is crucial:
- **Supply Chain Visibility Platforms**: Used to track product information across different stages of the supply chain.

## c. How do you approach the development problem?

1. **Requirements Gathering**: Understanding the user's needs and the various functional and non-functional aspects.
2. **Analysis**: Analysing the problem at hand, preferably breaking into smaller problems and coming up with solution for smaller problems and effectively solving the larger problem at hand.
7. **Testing and Quality Assurance**: Rigorous testing to ensure accuracy.
8. **Performance Optimization**: Tuning the system for handling large volumes of data efficiently depending on the constraints of the problem.

## d. What were interesting aspects where you copied code from Stack Overflow?

- Techniques for providing retry mechanism for API calls

## e. What did you learn from some very specific code adaptations?

- Got to learn about `Supplier` functional interface from Java 8 helping me to solve the exact problem at hand by which I ended up achieving excellent abstraction for making API calls to 3rd party and retrying in case of failures satisfying condition to retry.


### Database Migration Verification:

1. **Main Components:**
    - `validateMigration`: The primary method to compare original and migrated data.
    - `checkRow`: A helper method to compare individual rows.

2. **validateMigration method:**
    - Checks if the original and migrated data have the same number of rows.
    - Iterates through each row, comparing original and migrated data.
    - Uses `checkRow` to check each row in detail.
    - Returns `true` if all data matches, `false` otherwise.

3. **checkRow method:**
    - Ensures both rows have the same set of keys (column names).
    - Compares the values for each key between the original and migrated row.
    - Returns `true` if all key-value pairs match, `false` otherwise.

4. **Checkpoints:**
    - Size comparison: Ensures the number of rows is the same.
    - Row-by-row comparison: Checks each row for exact matches.

5. **Potential Improvements:**
    - Add logging for more detailed mismatch information.
    - Implement parallel processing for large datasets.
    - Maybe also check using checksum for better accuracy.

### JSON Parser

1. **Main Components:**
   - `parse`: The entry point for parsing JSON strings.
   - `parseValue`: Determines the type of JSON value and calls the appropriate parsing method.
   - Specific parsing methods for objects, arrays, strings, numbers, booleans, and null.

2. **Parse Method:**
   - Takes a JSON string as input and returns an Object.
   - Utilizes `parseValue` to handle the actual parsing.

3. **ParseValue Method:**
   - Checks the first character of the current position to determine the type of value.
   - Calls the appropriate parsing method based on the detected type.

4. **ParseObject Method:**
   - Creates a `LinkedHashMap` to maintain the order of key-value pairs.
   - Parses key-value pairs until it encounters a closing brace `}`.
   - Handles nested objects **recursively**.

5. **ParseArray Method:**
   - Creates an `ArrayList` to store array elements.
   - Parses values until it encounters a closing bracket `]`.
   - Handles nested arrays **recursively**.

6. **ParseString Method:**
   - Handles escape sequences, including Unicode escapes.
   - Builds the string character by character, accounting for escaped characters.

7. **ParseNumber Method:**
   - Iterated over string until current character is number, dot (.), or +, -
   - Creates a `BigDecimal` or `BigInteger` based on value type contains decimal point.
   
8. **Error Handling:**
   - Throws `IllegalArgumentException` for various parsing errors, providing descriptive error messages.

9. **Main Method:**
   - Demonstrates usage with a complex JSON example, including nested objects, arrays, and large/small numbers.
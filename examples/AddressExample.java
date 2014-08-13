import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSV;
import au.com.bytecode.opencsv.CSVReadProc;
import au.com.bytecode.opencsv.CSVWriteProc;
import au.com.bytecode.opencsv.CSVWriter;

/**
Copyright 2005 Bytecode Pty Ltd.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
public class AddressExample {

	private static final String ADDRESS_FILE="examples/addresses.csv";
	
	public static void main(String[] args) {
		
		final CSV csv = CSV.create();
		
		final List<String[]> container = new ArrayList<String[]>();
		csv.read(ADDRESS_FILE, new CSVReadProc() {
			public void procRow(int rowIndex, String... values) {
				container.add(values);
				System.out.println("Name: [" + values[0] + "]\nAddress: [" + values[1] + "]\nEmail: [" + values[2] + "]");
			}
		});
		
		// Try writing it back out as CSV to the console
		System.out.println("\n\nGenerated CSV File:\n\n");
		
		csv.write(System.out, new CSVWriteProc() {
			public void process(final CSVWriter out) {
				for (String[] values : container) {
					out.writeNext(values);
				}
			}
		});
	}
}

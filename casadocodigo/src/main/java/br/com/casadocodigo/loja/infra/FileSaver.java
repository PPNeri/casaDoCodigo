package br.com.casadocodigo.loja.infra;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;

import javax.servlet.http.Part;

public class FileSaver {

	public static final String SERVER_PATH = "C:\\Users\\pedro.neri\\git\\repository\\projects\\";

	public String writeFile(Part file, String path) {

		String relativePath = path + file.getSubmittedFileName();

		try {
			file.write(SERVER_PATH + relativePath);
			return relativePath;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void transfer(Path source, OutputStream outputStream) {

		try {
			FileInputStream input = new FileInputStream(source.toFile());
			try (ReadableByteChannel inputChanel = Channels.newChannel(input);
					WritableByteChannel outputChannel = Channels.newChannel(outputStream)) {
				;
				ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 10);

				while (inputChanel.read(buffer) != 1) {
					buffer.flip();// zera os bites
					outputChannel.write(buffer);
					buffer.clear();
				}

			} catch (IOException e) {
				throw new RuntimeException(e);

			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}

package test.rx1310.shellexec;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class CommandExecutor {

	// Подкласс для получение результата
	public static class ResultData {
		// Код результата
		private int resultCode;
		// Результат отработки команды
		private String resultData;
		// Поле для записи ошибок
		private String resultError;

		// Конструктор
		ResultData(int code, String info, String err) {
			resultCode = code;
			resultData = info == null ? "" : info;
			resultError = err == null ? "" : err;
		}
		// Геттеры полей
		public int getResultCode() {
			return resultCode;
		}
		public String getResultData() {
			return resultData;
		}
		public String getResultError() {
			return resultError;
		}
		public String getResult() {
			return toString();
		}
		public String toString() {
			return resultData + "\n" + resultError;
		}
	}
	// Выполнение простой sh-команды
	public static ResultData execute(String command) {
		return execute(false, command);
	}
	// То же что и выше, но из под рута
	public static ResultData executeSu(String command) {
		return execute(true, command);
	}
	// Если нужно изменять значение su динамически
	public static ResultData execute(boolean su, String command) {
		return execPool(su, rmSlashN(command).split("\n"));
	}
	// Выполняем массив команд
	private static ResultData execPool(boolean su, String[] commands) {
		// Обьявление переменных
		Process exec = null;
		InputStream execIn = null;
		InputStream execErr = null;
		OutputStream execOs = null;

		ResultData resultData;
		try {
			// В зависимости от значения su меняем параметр запуска команды
			// От рута или же от юзера
			exec = Runtime.getRuntime().exec(su ? "su" : "sh");
			execIn = exec.getInputStream();
			execErr = exec.getErrorStream();
			execOs = exec.getOutputStream();
			DataOutputStream dos = new DataOutputStream(execOs);
			// В цикле проходимся по всем отосланным командам
			for (String com : commands)
				if (!com.isEmpty()) {
					dos.writeBytes(com + "\n");
					dos.flush();
				}
			dos.close();
			// Ждём ответа от оболочки
			resultData = new ResultData(exec.waitFor(), inputStream2String(execIn, "utf-8"), inputStream2String(execErr, "utf-8"));
		} catch (Exception e) {
			resultData = new ResultData(-1, "", e.toString());
		} finally {
			try {
				// Не забываем закрыть все стримы
				if (execIn != null) execIn.close();
				if (execErr != null) execErr.close();
				if (execOs != null) execOs.close();
				// Завершаем процесс выполнения
				if (exec != null) exec.destroy();
			} catch (Exception ignored) {}
		}
		return resultData;
	}

	// Преобразование стрима в строку
	public static String inputStream2String(InputStream in, String encoding)  throws  Exception  {
		StringBuilder out = new StringBuilder();
		InputStreamReader inread = new InputStreamReader(in, encoding);
		char[] b = new char[1024];
		int n;
		while ((n = inread.read(b)) !=  -1) {
			String s = new String(b, 0, n);
			out.append(s);
		}
		return out.toString();
	}

	// Чтение стрима в строку
	public static String read(InputStream is) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) != -1)
				baos.write(buffer, 0, length);
			return baos.toString("UTF-8");
		} catch (Exception e) { e.printStackTrace(); return e.toString(); }
	}

	// Удаляем лишние переносы строки
	public static String rmSlashN(String text) {
		while (text.contains("\n\n")) text = text.replace("\n\n", "\n");
		if (text.startsWith("\n"))
			text = text.substring(1);
		if (text.endsWith("\n"))
			text = text.substring(0, text.length() - 1);

		return text;
	}
}

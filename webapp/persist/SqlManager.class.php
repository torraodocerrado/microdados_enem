<?php

	/**
	 * @author João Pedro Pinheiro
	 * @version 1.0
	 * 
	 */
	
	class SqlManager
	{
		private $conn;
		
		public function __construct ($fileName)
		{
			if ( file_exists ( "../config/{$fileName}.ini" ) )
				$dbInfo = parse_ini_file ( "../config/{$fileName}.ini" );
			else
				throw new Exception ( "Arquivo {$fileName} não encontrado!" );
			
			$user = $dbInfo["user"];
			$pass = $dbInfo["pass"];
			$host = $dbInfo["host"];
			$name = $dbInfo["name"];
			$type = $dbInfo["type"];
			$port = $dbInfo["port"];
			
			try
			{
				switch ( $type )
				{
					case "pgsql":
						$this->conn = new PDO ("pgsql:host={$host};port={$port};dbname={$name}",$user,$pass);
						break;
					case "mysql":
						$this->conn = new PDO ("mysql:host={$host};dbname={$name}",$user,$pass);
						break;
					case "sqlite":
						$this->conn = new PDO ("sqlite:{$name}");
						break;
					case "ibase":
						$this->conn = new PDO ("firebird:dbname={$name}",$user,$pass);
						break;
					case "oci8":
						$this->conn = new PDO ("oci:dbname={$name}",$user,$pass);
						break;
					case "mssql":
						$this->conn = new PDO ("mssql:host={$host},{$port};dbname={$name}",$user,$pass);
						break;
				}
			}
			catch ( Exception $e )
			{
				echo $e->getFile() . "<br/>" . "Line: " . $e->getLine() . "<br/>" . $e->getMessage() 
								   . "<br/>" . "<a href=\"javascript:history.go(-1)\">Voltar</a>";
				exit();
			}
		}
		
		public function executeRead ( $query )
		{
			try
			{
				$result = $this->conn->query($query);
				return $result;
			}
			catch ( Exception $e)
			{
				echo $e->getFile() . "<br/>" . "Line: " . $e->getLine() . "<br/>" . $e->getMessage() 
								   . "<br/>" . "<a href=\"javascript:history.go(-1)\">Voltar</a>";
				exit();
			}
		}
		
		public function executeCommand ( $query )
		{
			try
			{
				$affectedLines = $this->conn->exec($query);
				return $affectedLines;
			}
			catch ( Exception $e)
			{
				echo $e->getFile() . "<br/>" . "Line: " . $e->getLine() . "<br/>" . $e->getMessage() 
								   . "<br/>" . "<a href=\"javascript:history.go(-1)\">Voltar</a>";
				exit();
			}
		}
		
		public function closeConnection()
		{
			$this->conn = null;
		}
	}
?>
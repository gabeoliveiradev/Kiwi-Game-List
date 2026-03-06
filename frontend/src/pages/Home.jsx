import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";

export default function Home() {
  const [searchTerm, setSearchTerm] = useState("");
  const [games, setGames] = useState([]);
  const navigate = useNavigate();

  function handleLogout() {
    localStorage.removeItem("kiwi_token");
    navigate("/");
  }

  async function handleSearch(e) {
    e.preventDefault();

    if (!searchTerm) return;

    try {
      const response = await api.get(`/games/search?query=${searchTerm}`);
      setGames(response.data);
    } catch (error) {
      alert("Erro ao buscar jogos. Seu token pode ter expirado.");
      console.error(error);
    }
  }

  async function handleAddGame(gameId) {
    try {
      const gameNutella = games.find((g) => g.id === gameId);

      await api.post("/my-games", {
        id: gameNutella.id,
        name: gameNutella.name,
        backgroundImage: gameNutella.backgroundImage,
        rating: gameNutella.rating,
      });

      alert("Jogo adicionado à sua lista com sucesso! 🕹️");
    } catch (error) {
      console.error(error);
      alert("Erro ao adicionar. Talvez você já tenha esse jogo na lista?");
    }
  }

  return (
    <div className="min-h-screen bg-zinc-900 text-white p-8">
      {/* CABEÇALHO */}
      <header className="flex justify-between items-center mb-10 border-b border-zinc-700 pb-4">
        <h1
          className="text-3xl font-bold text-green-400 cursor-pointer"
          onClick={() => navigate("/games")}
        >
          Kiwi Game List 🥝
        </h1>
        <div className="flex gap-6 items-center">
          <button
            onClick={() => navigate("/minha-lista")}
            className="bg-zinc-800 px-4 py-2 rounded text-zinc-300 hover:text-white hover:bg-zinc-700 font-bold transition-colors"
          >
            Minha Lista
          </button>

          <button
            onClick={handleLogout}
            className="text-red-400 hover:text-red-300 font-bold"
          >
            Sair
          </button>
        </div>
      </header>

      {/* BARRA DE BUSCA */}
      <div className="flex justify-center mb-10">
        <form onSubmit={handleSearch} className="flex gap-2 w-full max-w-2xl">
          <input
            type="text"
            placeholder="Qual jogo você procura? (ex: Mario, The Witcher)"
            className="w-full bg-zinc-800 p-4 rounded-lg outline-none focus:ring-2 focus:ring-green-500 text-lg"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
          <button
            type="submit"
            className="bg-green-600 px-8 rounded-lg font-bold hover:bg-green-500 transition-colors"
          >
            BUSCAR
          </button>
        </form>
      </div>

      {/* LISTA DE JOGOS (RESULTADOS) */}
      <div className="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-6">
        {games.map((game) => (
          <div
            key={game.id}
            className="bg-zinc-800 rounded-lg overflow-hidden shadow-lg hover:scale-110 transition-transform"
          >
            <img
              src={game.backgroundImage}
              alt={game.name}
              className="w-full h-48 object-cover"
            />
            <div className="p-4">
              <h3 className="font-bold text-lg truncate">{game.name}</h3>
              <p className="text-zinc-400 text-sm">Rating: {game.rating} ⭐</p>

              <button
                onClick={() => handleAddGame(game.id)}
                className="mt-4 w-full bg-zinc-700 py-2 rounded text-sm hover:bg-zinc-600 active:bg-green-700 focus:outline-none"
              >
                + Adicionar à Lista
              </button>
            </div>
          </div>
        ))}
      </div>

      {games.length === 0 && (
        <p className="text-center text-zinc-500 mt-20 text-xl">
          Digite o nome de um jogo acima para começar...
        </p>
      )}
    </div>
  );
}

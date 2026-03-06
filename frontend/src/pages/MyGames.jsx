import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";

export default function MyGames() {
  const [myGames, setMyGames] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    async function loadMyGames() {
      try {
        const response = await api.get("/my-games");
        setMyGames(response.data);
      } catch (error) {
        console.error("Erro ao carregar a lista:", error);
        alert("Erro ao carregar sua lista de jogos.");
      }
    }
    loadMyGames();
  }, []);

  async function handleStatusChange(gameId, novoStatus) {
    try {
      await api.put(`/my-games/${gameId}/status`, { status: novoStatus });
      
      setMyGames(prevGames => 
        prevGames.map(item => 
          item.game.id === gameId ? { ...item, status: novoStatus } : item
        )
      );
    } catch (error) {
      console.error(error);
      alert('Erro ao atualizar o status do jogo.');
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
        <div className="flex gap-4">
          <button
            onClick={() => navigate("/games")}
            className="bg-zinc-800 px-4 py-2 rounded text-zinc-300 hover:text-white hover:bg-zinc-700 font-bold transition-colors"
          >
            Buscar Jogos
          </button>
          <button
            onClick={() => {
              localStorage.removeItem("kiwi_token");
              navigate("/");
            }}
            className="text-red-400 hover:text-red-300 font-bold"
          >
            Sair
          </button>
        </div>
      </header>

      <h2 className="text-2xl font-bold mb-6">
        Minha Lista ({myGames.length} jogos)
      </h2>

      {/* LISTA DE JOGOS SALVOS */}
      <div className="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-6">
        {myGames.map((item) => (
          <div
            key={item.id}
            className="bg-zinc-800 rounded-lg overflow-hidden shadow-lg border border-zinc-700"
          >
            <img
              src={item.game.backgroundImage}
              alt={item.game.name}
              className="w-full h-48 object-cover opacity-90"
            />
            <div className="p-4">
              <h3 className="font-bold text-lg truncate">{item.game.name}</h3>
              <p className="text-zinc-400 text-sm">
                Rating: {item.game.rating} ⭐
              </p>

              {/* STATUS DO JOGO */}
              <div className="mt-4 flex justify-between items-center text-sm">
                <span className="text-green-400 font-bold">✓ Salvo</span>
                
                <select 
                  value={item.status || ""} 
                  onChange={(e) => handleStatusChange(item.game.id, e.target.value)}
                  className="bg-zinc-700 text-zinc-200 border border-zinc-600 rounded p-1 outline-none focus:ring-1 focus:ring-green-500 cursor-pointer"
                >
                  <option value="" disabled>Selecione...</option>
                  <option value="JOGANDO">🎮 Jogando</option>
                  <option value="ZERADO">🏆 Finalizado</option>
                  <option value="ABANDONADO">🛑 Abandonado</option>
                  <option value="PLANEJANDO">🕒 Na Fila</option>
                </select>
                </div>
            </div>
          </div>
        ))}
      </div>

      {/* SE NÃO TIVER NADA NA LISTA AINDA */}
      {myGames.length === 0 && (
        <div className="text-center text-zinc-500 mt-20">
          <p className="text-xl">Sua lista está vazia!</p>
          <button
            onClick={() => navigate("/games")}
            className="mt-4 text-green-400 underline hover:text-green-300"
          >
            Voltar e buscar jogos
          </button>
        </div>
      )}
    </div>
  );
}

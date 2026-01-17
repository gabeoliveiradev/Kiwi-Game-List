import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import Login from './pages/Login'
import Home from './pages/Home'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* Se acessar a raiz (/), vai pro Login */}
        <Route path="/" element={<Login />} />
        
        {/* Rota principal dos jogos */}
        <Route path="/games" element={<Home />} />

        {/* Qualquer outra rota joga pro login */}
        <Route path="*" element={<Navigate to="/" />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
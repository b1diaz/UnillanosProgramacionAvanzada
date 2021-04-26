using Microsoft.EntityFrameworkCore;
using Spofy.DataAccess.Repository;
using Spofy.Models;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace Spofy.Services
{
    public class AutorServices {
        #region --------- Dependencias ------------
        private readonly IRepository<Autor> _AutorRepository;

        public AutorServices(IRepository<Autor> AutorRepository)
        {
            this._AutorRepository = AutorRepository;
        }
        #endregion

        #region --------------- GET -------------------
        /// <summary>
        /// Obtiene una lista de autores
        /// </summary>
        /// <returns></returns>
        public async Task<List<Autor>> GetAll()
        {
            var Autores = await this._AutorRepository.Query()
                .ToListAsync();

            return Autores;
        }

        /// <summary>
        /// Cunsulta un Autor por su Id
        /// </summary>
        /// <param name="AutorId"></param>
        /// <returns></returns>
        public async Task<Autor> Get(long AutorId)
        {
            var Autor = await this._AutorRepository.Query().FirstOrDefaultAsync(x => x.Id == AutorId);

            return Autor;
        }

        #endregion

        #region ----------------- POST -----------------
        /// <summary>
        /// Crea un autor
        /// </summary>
        /// <param name="Autor"></param>
        /// <returns></returns>
        public async Task Create(Autor Autor)
        {
            try
            {
                await this._AutorRepository.AddAsync(Autor);
                this._AutorRepository.SaveChange();
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        #endregion

        #region ------------- UPDATE ------------------
        /// <summary>
        /// Actaliza un autor
        /// </summary>
        /// <param name="Autor"></param>
        /// <returns></returns>
        public async Task Update(Autor Autor)
        {
            try
            {
                var AutorAnterior = await this._AutorRepository.Query().FirstOrDefaultAsync(x => x.Id == Autor.Id);

                AutorAnterior.Nombre = Autor.Nombre;
                this._AutorRepository.Update(AutorAnterior);
                this._AutorRepository.SaveChange();
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
        #endregion

        #region ---------------- DELETE ------------------
        /// <summary>
        /// Elimina un autor por su Id
        /// </summary>
        /// <param name="AutorId"></param>
        /// <returns></returns>
        public async Task Delete (long AutorId)
        {
            var Autor = await this._AutorRepository.Query().FirstOrDefaultAsync(x => x.Id == AutorId);
            this._AutorRepository.Remove(Autor);
            this._AutorRepository.SaveChange();

        }
        #endregion
    }
}
